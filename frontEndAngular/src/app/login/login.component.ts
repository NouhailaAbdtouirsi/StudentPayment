import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  public loginForm!: FormGroup;
  constructor(private fb:FormBuilder,
              private authService: AuthService,
              private router: Router) {
  }
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: this.fb.control(''),
      password: this.fb.control('')
    });

  }

  login() {
    let username = this.loginForm.get('username')?.value;
    let password = this.loginForm.get('password')?.value;
    let auth : boolean = this.authService.login(username, password);
    if(auth) {
      this.router.navigate(['admin']);
    } else {
      alert('Invalid username or password');
    }
  }
}
