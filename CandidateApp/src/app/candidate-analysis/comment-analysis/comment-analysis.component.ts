import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comment-analysis',
  templateUrl: './comment-analysis.component.html',
  styleUrls: ['./comment-analysis.component.scss']
})
export class CommentAnalysisComponent implements OnInit {

  constructor() {
    document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  ngOnInit() {
  }

}
