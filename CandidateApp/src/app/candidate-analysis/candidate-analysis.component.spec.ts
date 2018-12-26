import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateAnalysisComponent } from './candidate-analysis.component';

describe('CandidateAnalysisComponent', () => {
  let component: CandidateAnalysisComponent;
  let fixture: ComponentFixture<CandidateAnalysisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidateAnalysisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidateAnalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
