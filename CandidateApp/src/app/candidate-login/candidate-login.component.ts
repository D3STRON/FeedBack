import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';


@Component({
  selector: 'app-candidate-login',
  templateUrl: './candidate-login.component.html',
  styleUrls: ['./candidate-login.component.scss']
})


export class CandidateLoginComponent implements OnInit {

  password : string;
  refId : string;
  
  constructor(private auth: AuthService) {
     document.body.style.background = 'rgba(4,89,152,0.25)';
  }

  goToPage(){
     this.auth.loginUser(this.refId,this.password)
  }
  
  ngOnInit() {
    
  }
}
