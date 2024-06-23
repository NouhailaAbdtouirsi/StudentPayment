import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public users: any={
    admin : {
      password : 'admin',
      roles : ['ADMIN', 'STUDENT']
    },
    user1 : {
      password : '1234',
      roles : ['STUDENT']
    }
  }
  public userName : any;
  public roles : string[] = [];
  public isAuthenticated : boolean = false;

  constructor() { }
  public login(username: string, password: string) : boolean {
    if(this.users[username] && this.users[username].password == password) {
      this.isAuthenticated = true;
      this.userName = username;
      this.roles = this.users[username].roles;
      return true;
    }else {
      this.isAuthenticated = false;
      this.userName = '';
      this.roles = [];
      return false;
    }
  }
}
