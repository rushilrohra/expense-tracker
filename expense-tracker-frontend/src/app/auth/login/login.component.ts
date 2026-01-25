import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private authService : AuthService, private router : Router) {}

  loginFormGroup = new FormGroup({
    email : new FormControl('',[Validators.required,Validators.email]),
    password : new FormControl('',[Validators.required])
  })

  getFormControl(name:string){
    return this.loginFormGroup.get(name);
  }

  isFormControlError(name:string){
    return this.getFormControl(name)?.dirty && this.getFormControl(name)?.invalid && this.getFormControl(name)?.errors?.['required']
  }

  errorMessage:any;
  submitForm(){
    this.authService.loginUser(this.getFormControl('email')?.value,this.getFormControl('password')?.value).subscribe({
      next : () => {
        this.loginFormGroup.reset()
        this.router.navigate(['/'])
      },
      error : (err:any) =>  this.errorMessage = err
    });
  }
}