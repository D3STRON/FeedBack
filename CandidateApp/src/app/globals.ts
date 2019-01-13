import { Injectable } from '@angular/core';

@Injectable()
export class Globals {
  url: string = 'http://localhost:8080/';
  getQuestions: string = 'questions/all';
  feedback: string = 'feedback'
  login: string = 'login'
  length: Number = 50;
}
