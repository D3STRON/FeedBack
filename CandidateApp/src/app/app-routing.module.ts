import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CandidateLoginComponent } from './candidate-login/candidate-login.component';
import { CandidateFeedbackComponent } from './candidate-feedback/candidate-feedback.component';

const routes: Routes = 
[{ path: 'login', component: CandidateLoginComponent },
{  path: 'feedback', component: CandidateFeedbackComponent},
{path: '', redirectTo: 'login', pathMatch: 'full' } //redirecting
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
