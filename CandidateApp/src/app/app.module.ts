import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CookieService } from 'angular2-cookie/services/cookies.service'; // for cookies
import { CandidateLoginComponent } from './candidate-login/candidate-login.component';
import { CandidateFeedbackComponent } from './candidate-feedback/candidate-feedback.component';
import { FormsModule } from '@angular/forms'; // for tow way data binding
import { HttpClientModule } from '@angular/common/http'; // for http request 
import { Globals } from './globals' // global variables

@NgModule({
  declarations: [
    AppComponent,
    CandidateLoginComponent,
    CandidateFeedbackComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // for tow way data binding
    HttpClientModule // for http request
  ],
  providers: [
    CookieService,
    Globals
  ], // for cookies
  bootstrap: [AppComponent]
})
export class AppModule { }
