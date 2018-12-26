import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { YearlyAnalysisComponent } from './yearly-analysis.component';

describe('YearlyAnalysisComponent', () => {
  let component: YearlyAnalysisComponent;
  let fixture: ComponentFixture<YearlyAnalysisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ YearlyAnalysisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(YearlyAnalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
