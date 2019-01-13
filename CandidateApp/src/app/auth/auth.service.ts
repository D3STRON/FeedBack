import { Injectable } from '@angular/core';
import { CookieService } from 'angular2-cookie/core'; // for cookies
import { HttpClient } from '@angular/common/http'; // for http request
import { Globals } from '../globals';
import { Router } from '@angular/router';// for routing

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  readonly pageName = 'login';
  cookieService: CookieService;

  constructor(private router : Router, private http: HttpClient, private globals: Globals) {
    this.cookieService = new CookieService()
   }

  loginUser(refId, password)
  {
    this.http.post(this.globals.url+this.pageName,
      {
        refId: refId,
        password:password
      })
      .subscribe(
          data => {
              console.log("POST Request is successful ", data);
              if(data['success'])
              {
                this.cookieService.removeAll()
                this.cookieService.put("JWTtoken",data['token']);
                this.router.navigate([this.globals.feedback]);
              }
          },
          error => {
              console.log("Error has occured", error);
          }
      );
  }
}
