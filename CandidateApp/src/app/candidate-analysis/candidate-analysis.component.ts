import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // for http request 
import { Globals } from '../globals';

@Component({
  selector: 'app-candidate-analysis',
  templateUrl: './candidate-analysis.component.html',
  styleUrls: ['./candidate-analysis.component.scss']
})
export class CandidateAnalysisComponent implements OnInit {

  question: string;
  questionList: Array<any>;
  readonly pageName = 'feedback';
  readonly addQPage = 'addQuestion';

  constructor(private httpClient : HttpClient, private g : Globals) {
    this.questionList = new Array()
    document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  addQuestion()
  {
    this.questionList.push({questionNo: 'Q'+(this.questionList.length+1), questionName: this.question})
    this.httpClient.post(this.g.url+this.addQPage,
      {
        question: this.question
      })
      .subscribe(
          data => {
              console.log("POST Request is successful ", data);
          },
          error => {
              console.log("Error", error);
          }
      );
  }

  ngOnInit() {
    this.httpClient.get(this.g.url+this.pageName).subscribe(data => {
      for(let i =0; i< 4;i++)
      {
        this.questionList.push({questionNo: 'Q'+(i+1), questionName: data[i].questionName})
      }
    },
    error => {
        console.log("Error", error);
    }
  );
  }

}
