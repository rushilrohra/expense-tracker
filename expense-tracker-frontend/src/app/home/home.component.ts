import { Component, Input } from '@angular/core';


@Component({
  selector: 'app-home',
  template: `
    <h1 style="color:red">HOME INLINE TEMPLATE</h1>
    <app-navbar></app-navbar>
  `,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  
}