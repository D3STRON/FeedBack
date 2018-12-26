import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-yearly-analysis',
  templateUrl: './yearly-analysis.component.html',
  styleUrls: ['./yearly-analysis.component.scss']
})
export class YearlyAnalysisComponent implements OnInit {

  constructor() {
    document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  ngOnInit() {
  }

}
