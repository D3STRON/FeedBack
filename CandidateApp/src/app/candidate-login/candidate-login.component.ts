import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';// for routing
import { HttpClient } from '@angular/common/http'; // for http request 
import {CookieService} from 'angular2-cookie/core'; // for cookies for tokens
import { Globals } from '../globals';



@Component({
  selector: 'app-candidate-login',
  templateUrl: './candidate-login.component.html',
  styleUrls: ['./candidate-login.component.scss']
})


export class CandidateLoginComponent implements OnInit {

  readonly pageName = 'login';
  password : string;
  refId : string;
  cookieService: CookieService;
  
  constructor(private router: Router, private httpClient: HttpClient, private g: Globals) {
     document.body.style.background = 'rgba(4,89,152,0.25)';
     this.cookieService = new CookieService()
  }

  goToPage(pageName:string){
    
    this.httpClient.post(this.g.url+this.pageName,
      {
        refId: this.refId,
        password:this.password
      })
      .subscribe(
          data => {
              console.log("POST Request is successful ", data);
              if(data['success'])
              {
                this.cookieService.removeAll()
                this.cookieService.put("JWTtoken",data['token']);
                this.router.navigate([`${pageName}`]);
              }
          },
          error => {
              console.log("Error has occured", error);
          }
      ); 
  }
  
  ngOnInit() {
    
  }
  // code for get requests
  // this.http.get(this.URL).subscribe(data => {
  //   console.log(data[0]);
  // }
  // );
}
