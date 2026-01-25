import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http :HttpClient) { }

  private registerUrl = "http://localhost:8080/register";
  private loginUrl = "http://localhost:8080/login";
  private forgotPwd = "http://localhost:8080/forgot-password"
  private validatePwd = "http://localhost:8080/validate-token"
  registerUser(user:any){
    console.log(user);
    return this.http.post(this.registerUrl,user);
  }

  loginUser(email:any,password:any){
    return this.http.post(this.loginUrl,email,password);
  }

  resetPwd(email:any){
    return this.http.post(this.forgotPwd,email);
  }
  validateToken(token:any){
    return this.http.post(this.validatePwd,{token});
  }
}