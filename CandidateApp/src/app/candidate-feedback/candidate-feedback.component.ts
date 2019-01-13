import { Component, OnInit } from '@angular/core';
import { CookieService } from 'angular2-cookie/core'; // for cookies
import { HttpClient } from '@angular/common/http'; // for http request
import { Globals } from '../globals';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';// for routing

@Component({
  selector: 'app-candidate-feedback',
  templateUrl: './candidate-feedback.component.html',
  styleUrls: ['./candidate-feedback.component.scss']
})
export class CandidateFeedbackComponent implements OnInit {
  title: string;
  cookieService: CookieService;
  questionList: Array<any>;
  feedback: string;
  readonly pageName = 'feedback';
  myDate = new Date();

  constructor(
    private httpClient: HttpClient,
    private g: Globals,
    private datePipe: DatePipe,
    private router: Router
  ) {
    document.body.style.background = 'rgba(4,89,152,0.25)';
    this.title = 'TIAA CANDIDATE FEEDBACK';
    this.cookieService = new CookieService();
    this.questionList = new Array();
   }

  setScore(a, b) {
    this.questionList[a].questionRating = b;
    if (b < 8) {
      this.questionList[a].questionOverAll = 'Average';
    } else if (b < 10) {
      this.questionList[a].questionOverAll = 'Satisfactory';
    } else {
      this.questionList[a].questionOverAll = 'Excellent';
    }
  }

  submitFeedback() {
    console.log(this.datePipe.transform(this.myDate, 'yyyy-MM-dd'));/// convert date to a format given
    this.httpClient
      .post(this.g.url + this.pageName, {
        // dateString: this.datePipe.transform(this.myDate, 'yyyy-MM-dd'),
        candidateCompanyName: 'Jp Morgan',
        feedback: this.feedback,
        questionsAttempted: this.questionList
      })
      .subscribe(
        data => {
          console.log('POST Request is successful ', data);
        },
        error => {
          console.log('Error', error);
        }
      );
  }

  ngOnInit() {
      this.httpClient.get(this.g.url + this.g.getQuestions,{
        headers: {'Authorization':this.cookieService.get('CNtoken')}
     }).subscribe(
        data => {
           if(data){for (let i = 0; i < data["length"]; i++) {
            this.questionList.push({
              questionNo: (i + 1),
              questionName: data[i].questionName,
              questionOverAll: '?',
              questionRating: null,
              visible: data[i].visible
            });
          }}
          else {
            this.router.navigate([`${'login'}`]);
          }
        },
        error => {
          console.log('Error', error);
          this.router.navigate([`${'login'}`]);
        }
      );
    }
}
