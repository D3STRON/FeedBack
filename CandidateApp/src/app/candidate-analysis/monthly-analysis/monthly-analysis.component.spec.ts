import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyAnalysisComponent } from './monthly-analysis.component';

describe('MonthlyAnalysisComponent', () => {
  let component: MonthlyAnalysisComponent;
  let fixture: ComponentFixture<MonthlyAnalysisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonthlyAnalysisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthlyAnalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
