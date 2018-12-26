import { Component, OnInit } from '@angular/core';
import {CookieService} from 'angular2-cookie/core'; // for cookies
import { HttpClient } from '@angular/common/http'; // for http request 
import { Globals } from '../globals';

@Component({
  selector: 'app-candidate-feedback',
  templateUrl: './candidate-feedback.component.html',
  styleUrls: ['./candidate-feedback.component.scss']
})



export class CandidateFeedbackComponent implements OnInit{

  title : string;
  cookieService: CookieService;
  questionList: Array<any>;
  feedback: string;
  readonly pageName = 'feedback'

  constructor(private httpClient : HttpClient, private g: Globals){
    document.body.style.background = 'rgba(4,89,152,0.25)';
    this.title = 'TIAA CANDIDATE FEEDBACK';
    this.cookieService = new CookieService();  
    this.questionList = new Array();
  }
  
  setScore(a,b) {
    this.questionList[a].questionRating = b;
    if(b<8)
    {
      this.questionList[a].questionOverAll = 'Average'
    }
    else if(b<10)
    {
      this.questionList[a].questionOverAll = 'Satisfactory'
    }
    else{
      this.questionList[a].questionOverAll = 'Excellent'
    }
  }

  setCookie()
  {
    this.cookieService.put('test','testingCookie');
  }

  submitFeedback()
  {
      this.httpClient.post(this.g.url+this.pageName,
      {
        candidateCompanyName: 'Jp Morgan',
        feedback:this.feedback,
        questionList: this.questionList
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

  ngOnInit()
  {
    this.httpClient.get(this.g.url+this.pageName).subscribe(data => {
        for(let i =0; i< 4;i++)
        {
          this.questionList.push({questionNo: 'Q'+(i+1), questionName: data[i].questionName, questionOverAll:'?', questionRating: null})
        }
      },
      error => {
          console.log("Error", error);
      }
    );
    // for(let i =0; i< 5;i++)
    // {
    //   this.questionList.push({questionNo: 'Q'+i, questionName: 'How was the food?', questionOverAll:'?', questionRating: null})
    // }
  }

}
