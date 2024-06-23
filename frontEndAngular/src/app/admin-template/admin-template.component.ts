import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrl: './admin-template.component.css'
})
export class AdminTemplateComponent {
  constructor(public authService: AuthService,
              public router: Router
  ) {
  }

  logout() {
    this.authService.isAuthenticated = false;
    this.authService.userName = '';
    this.authService.roles = [];
    this.router.navigate(['login']);
  }
}
