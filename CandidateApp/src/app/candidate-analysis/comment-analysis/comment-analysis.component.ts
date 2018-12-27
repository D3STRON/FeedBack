import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js';
@Component({
  selector: 'app-comment-analysis',
  templateUrl: './comment-analysis.component.html',
  styleUrls: ['./comment-analysis.component.scss']
})
export class CommentAnalysisComponent implements OnInit {
  PieChart=[];
  constructor() {
    document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  ngOnInit() {
    this.PieChart=new Chart ('piechart', {
      type: 'pie',
      data: {
        labels: ["Positive Comments", "Negative Comments"],
        datasets: [{
          label: "Polarity",
          backgroundColor: [ "#8e5ea2","#c45850",],
          data: [60,40]
        }]
      },
      options: {
        title: {
          display: true,
          text: 'Sentiment Analysis of Feedback Comments'
        }
      }
    });
  }

}

