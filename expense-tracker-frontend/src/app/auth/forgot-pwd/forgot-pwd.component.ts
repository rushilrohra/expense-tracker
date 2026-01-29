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

  
  errorMessage = ""
  sendResetLink() {
    if (this.forgotFormGroup.valid) {
      console.log('Sending reset link to:', this.forgotFormGroup.value.email);
      this.authService.forgotPassword(this.forgotFormGroup.value.email).subscribe({
        next : () => alert("Email has been sent to your id"),
        error : () => this.errorMessage = "Some Error Occurred"
      })
    }
  }
}