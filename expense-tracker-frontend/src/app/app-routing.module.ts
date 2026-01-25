import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { ForgotPwdComponent } from './auth/forgot-pwd/forgot-pwd.component';
import { ResetPwdComponent } from './auth/reset-pwd/reset-pwd.component';

const routes: Routes = [
  {
    path : '',
    component : HomeComponent
  },
  {
    path : 'register',
    component : RegisterComponent
  },
  {
    path : 'login',
    component : LoginComponent
  },
  {
    path : 'forgot-pwd',
    component : ForgotPwdComponent
  },
  {
    path : 'reset-pwd/:token',
    component : ResetPwdComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }