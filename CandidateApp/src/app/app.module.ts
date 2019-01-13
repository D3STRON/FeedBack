import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CookieService } from 'angular2-cookie/services/cookies.service'; // for cookies
import { CandidateLoginComponent } from './candidate-login/candidate-login.component';
import { CandidateFeedbackComponent } from './candidate-feedback/candidate-feedback.component';
import { FormsModule } from '@angular/forms'; // for tow way data binding
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; // for http request and tokenn handeling
import { Globals } from './globals';
import { CandidateAnalysisComponent } from './candidate-analysis/candidate-analysis.component';
import { HrLoginComponent } from './hr-login/hr-login.component';
import { MonthlyAnalysisComponent } from './candidate-analysis/monthly-analysis/monthly-analysis.component';
import { YearlyAnalysisComponent } from './candidate-analysis/yearly-analysis/yearly-analysis.component';
import { CommentAnalysisComponent } from './candidate-analysis/comment-analysis/comment-analysis.component' // global variables
import { DatePipe } from '@angular/common';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';
import { TokenInterceptorService } from './auth/token-interceptor.service';


@NgModule({
  declarations: [
    AppComponent,
    CandidateLoginComponent,
    CandidateFeedbackComponent,
    CandidateAnalysisComponent,
    HrLoginComponent,
    MonthlyAnalysisComponent,
    YearlyAnalysisComponent,
    CommentAnalysisComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // for tow way data binding
    HttpClientModule // for http request

  ],
  providers: [
    CookieService,
    Globals,
    DatePipe,
    AuthService,
    AuthGuard,
    {
      provide : HTTP_INTERCEPTORS,
      useClass : TokenInterceptorService, // process of adding token interceptor to the project
      multi : true
    }
  ], // for cookies
  bootstrap: [AppComponent]
})
export class AppModule { }
