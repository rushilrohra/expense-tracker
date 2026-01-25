import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-forgot-pwd',
  templateUrl: './forgot-pwd.component.html',
  styleUrls: ['./forgot-pwd.component.css']
})
export class ForgotPwdComponent {

  constructor(private authService : AuthService, private router: Router) {}

  forgotFormGroup = new FormGroup({
    email : new FormControl('',[Validators.required,Validators.email])
  })


  isFormControlError(controlName: string){
    const control = this.forgotFormGroup.get(controlName);
    return control && control.invalid && (control.dirty || control.touched);
  }

  body:any;

  sendResetLink() {
    // if (this.forgotFormGroup.valid) {
    //   this.authService.resetPwd(this.forgotFormGroup.get("email")?.value).subscribe(()=>{
    //     next: ()=> {this.body = "Email sent to your id"},
    //     error: (err: any)=> this.body = "Email invalid"
        
    //   })  
    // }
  }
}