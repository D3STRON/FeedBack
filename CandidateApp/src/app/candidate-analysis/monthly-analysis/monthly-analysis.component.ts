import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-monthly-analysis',
  templateUrl: './monthly-analysis.component.html',
  styleUrls: ['./monthly-analysis.component.scss']
})
export class MonthlyAnalysisComponent implements OnInit {

  constructor() {
    document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  ngOnInit() {
  }

}
