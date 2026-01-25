import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private authService : AuthService, private router:Router) {}

  RegisterFormGroup = new FormGroup({
    name : new FormControl('',[Validators.required]),
    username : new FormControl('',[Validators.required]),
    email : new FormControl('',[Validators.required,Validators.email]),
    password : new FormControl('',[Validators.required]),
    mobile : new FormControl('',[Validators.required,Validators.pattern("^[0-9]{10}$")])
  });

  getFormControl(name:string){
    return this.RegisterFormGroup.get(name);
  }

  isFormControlError(name:string){
    return this.getFormControl(name)?.dirty && this.getFormControl(name)?.invalid && this.getFormControl(name)?.errors?.['required']
  }

  errorMessage:any;
  submitForm(){
    this.authService.registerUser(this.RegisterFormGroup.value).subscribe({
      next : () => {
        this.RegisterFormGroup.reset()
        this.router.navigate(['/'])
      },
      error : (err:any) => {
        console.log(err)
        this.errorMessage = err.error.Body
      }
    });
  }

}