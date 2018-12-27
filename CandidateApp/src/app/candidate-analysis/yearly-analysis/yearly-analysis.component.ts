import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js';
import { HttpClient } from '@angular/common/http'; // for http request 
import { Globals } from '../../globals';

@Component({
  selector: 'app-yearly-analysis',
  templateUrl: './yearly-analysis.component.html',
  styleUrls: ['./yearly-analysis.component.scss']
})
export class YearlyAnalysisComponent implements OnInit {
  BarChart;
  analysis;
  questionList: Array<any>;

  constructor(private httpClient : HttpClient, private g : Globals) {
    this.questionList = new Array();
    document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  drawGraph(questionNo)
  {
    var year = 2019;
    this.reinitalizeGraph()
        while(this.analysis[''+year]!==undefined)
        {
          this.BarChart.data.labels.unshift(''+year)
          this.BarChart.data.datasets[0].data.unshift(this.analysis[''+year][''+questionNo])
          var r1 = Math.round(Math.random()*255) 
          var r2 = Math.round(Math.random()*255)
          var r3 = Math.round(Math.random()*255)
          this.BarChart.data.datasets[0].backgroundColor.push('rgba('+r1+', '+r2+', '+r3+', 0.45)')
          this.BarChart.data.datasets[0].borderColor.push('rgba('+r1+', '+r2+', '+r3+', 1)')
          year -= 1;
        }
        this.BarChart.update()
  }
 

  reinitalizeGraph()
  {
    this.BarChart=new Chart ('barchart', {
      type: 'bar',
      data: {
        labels: [], //yeh dynamically [2018 to current_year] lena padega:
                                              // assuming ki hamara feed backack system 2018 se data collect karna start kiya
        datasets: [{
          label: '# of Votes',
          data: [],
          backgroundColor: [],
          borderColor: [],
          borderWidth: 2
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero:true
            }
          }]
        }
      }
    });
  }

  ngOnInit() {
        
    this.reinitalizeGraph()
    var num = new Number(266);
    this.httpClient.get(this.g.url+'feedback/yearly').subscribe(data => {
        this.analysis = data
    },
    error => {
        console.log("Error", error);
    }
   );

    this.httpClient.get(this.g.url+this.g.getQuestions).subscribe(data => {
      for(let i =0; i< data["length"];i++)
      {
        this.questionList.push({questionNo: (i+1), questionName: data[i].questionName})
      }
    },
    error => {
        console.log("Error", error);
    }
   );
  }

}
