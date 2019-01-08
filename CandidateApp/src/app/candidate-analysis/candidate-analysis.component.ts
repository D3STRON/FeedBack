import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // for http request
import { Globals } from '../globals';
import { CookieService, CookieOptions } from 'angular2-cookie/core'; 
import { findLast } from '@angular/compiler/src/directive_resolver';

@Component({
  selector: 'app-candidate-analysis',
  templateUrl: './candidate-analysis.component.html',
  styleUrls: ['./candidate-analysis.component.scss']
})
export class CandidateAnalysisComponent implements OnInit {
  question: string;
  questionList: Array<any>;

  readonly addQPage = 'addQuestion';

  constructor(private httpClient: HttpClient, private g: Globals, private cokieService: CookieService) {
    this.questionList = new Array();
    document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  find() {
    for (let i = 0; i < this.questionList.length; i++) {
      if (this.questionList[i].questionName === this.question) {
        return -1;
      }
    }
    return 0;
  }

  addQuestion() {
    if (this.find() == 0) {
      this.questionList.push({
        questionNo: (this.questionList.length + 1),
        questionName: this.question
      });
      this.httpClient
        .post(this.g.url + this.addQPage, {
          questionNo: this.questionList.length,
          questionName: this.question
        })
        .subscribe(
          data => {
            console.log('POST Request is successful ', data);
          },
          error => {
            console.log('Error', error);
          }
        );
    } else {
      alert('This question already exists!');
    }
  }

  ngOnInit() {
    if(this.cokieService.get("token"))
    {}    
    this.httpClient.get(this.g.url + this.g.getQuestions).subscribe(
      data => {
        console.log(data);

        for (let i = 0; i < data["length"]; i++) {
          this.questionList.push({
            questionNo: (i + 1),
            questionName: data[i].questionName,
            visible: data[i].visible
          });
        }
      },
      error => {
        console.log('Error', error);
      }
    );
  }
}
