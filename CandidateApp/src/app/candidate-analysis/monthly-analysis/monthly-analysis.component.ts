import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js';
import { HttpClient } from '@angular/common/http'; // for http request 
import { Globals } from '../../globals';

@Component({
  selector: 'app-monthly-analysis',
  templateUrl: './monthly-analysis.component.html',
  styleUrls: ['./monthly-analysis.component.scss']
})
export class MonthlyAnalysisComponent implements OnInit {
  BarChart; 
  analysis;
  questionList: Array<any>;
  fromYear: number = (new Date()).getFullYear();
  yearList: Array<any>;
  

  constructor(private httpClient : HttpClient, private g : Globals) {
    this.yearList = new Array();
    this.questionList = new Array();
    document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  setFromYear(year)
  {
     this.fromYear = year;
     this.initializeGraph()
     alert("Select the question!")
  }

  drawGraph(questionNo)
  {

    this.httpClient.get(this.g.url+"feedback/monthly?year="+this.fromYear).subscribe(data => {
      this.analysis = data;
    },
      error => {
          console.log("Error", error);
      }
    );

    this.reinitializeGraph()
    for(let i =1 ;i <= 12; i++)
    {
          this.BarChart.data.datasets[0].data.push(this.analysis[''+i][''+questionNo])
          var r1 = Math.round(Math.random()*255) 
          var r2 = Math.round(Math.random()*255)
          var r3 = Math.round(Math.random()*255)
          this.BarChart.data.datasets[0].backgroundColor.push('rgba('+r1+', '+r2+', '+r3+', 0.45)')
          this.BarChart.data.datasets[0].borderColor.push('rgba('+r1+', '+r2+', '+r3+', 1)')
    }
    this.BarChart.update()
  }

  reinitializeGraph()
  {
    this.BarChart.data.datasets[0].data = []
    this.BarChart.data.datasets[0].backgroundColor = []
    this.BarChart.data.datasets[0].borderColor = []
  }

  initializeGraph()
  {
    this.BarChart=new Chart ('barchart', {
      type: 'bar',
      data: {
        labels: ["Jan", "Feb", "March", "April", "May", "June","July","Aug","Sept","Oct","Nov","Dec"],
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
          this.initializeGraph()
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
        
          this.httpClient.get(this.g.url+'feedback/monthly?year='+this.fromYear).subscribe(data => {
            this.analysis = data
          },
            error => {
                console.log("Error", error);
            }
          );

          this.httpClient.get(this.g.url+'feedback/yearly').subscribe(data => {
            var  year = 2019    //Date.getFullYear()
            while(data[''+year]!==undefined)
            {
              this.yearList.push(''+year)
              year -=1
            }
          },
          error => {
              console.log("Error", error);
          }
          );
  }  
}
