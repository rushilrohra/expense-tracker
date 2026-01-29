import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-reset-pwd',
  templateUrl: './reset-pwd.component.html',
  styleUrls: ['./reset-pwd.component.css']
})
export class ResetPwdComponent {

  constructor(private authService : AuthService,private route:ActivatedRoute, private router: Router) {}
  token:any;
  ngOnInit(){
    this.token = this.route.snapshot.paramMap.get("token");
    this.authService.validateToken(this.token).subscribe({
      next : (data:any) => {console.log(data)},
      error : (err:any) => {
        alert("Your token is invalid");
        this.router.navigate(['/login']);
      }
    })
  }

  resetPwdGroup = new FormGroup({
    newPassword : new FormControl('',[Validators.required,Validators.minLength(6)]),
    confirmPassword : new FormControl('',[Validators.required]), 
  })

  isFormControlError(controlName: string){
    const control = this.resetPwdGroup.get(controlName);
    return control && control.invalid && (control.dirty || control.touched);
  }

  isPasswordMatches(){
    return this.resetPwdGroup.get('newPassword')?.value === this.resetPwdGroup.get('confirmPassword')?.value
  }

  resetPassword() {
    if (this.resetPwdGroup.valid) {
      const newPassword = this.resetPwdGroup.value.newPassword;
      this.token = this.route.snapshot.paramMap.get("token");
    this.authService.validateToken(this.token).subscribe({
      next : (data:any) => {
        this.authService.resetPassword(newPassword,this.token).subscribe(()=>{
          alert("Your Password is Successfuly reset");
          this.router.navigate(['/login']);
        })
      },
      error : () => {
        alert("Your token is invalid");
        this.router.navigate(['/login']);
      }
    })      
    }
  }

  
}