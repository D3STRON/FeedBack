import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';
import { CookieService } from 'angular2-cookie/core'; // for cookies

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  cookieService: CookieService;

  constructor() {
    this.cookieService = new CookieService();
  }

  intercept(req, next){
    let token = "" // make sure you have this else auth token will be set to null
    if(this.cookieService.get('CNtoken')){
      token = this.cookieService.get('CNtoken')
    }
    let tokenizedreq = req.clone({
      setHeaders : {
         Authorization : token // intercept evey http req and send auth token with it
      }
    })
    return next.handle(tokenizedreq);
  }
}
