import { Injectable } from '@angular/core';
import { User } from '../models/User';

@Injectable()
export class CurrentUserService {
  constructor (){}

  private user: User;

  setUser(user: User){ 
    this.user = user;

    sessionStorage.setItem("user", JSON.stringify(this.user));

    console.log("user set to: {id:"+this.user.id+",first name:"+this.user.firstName+",last name:"+this.user.lastName+",email:"+this.user.email+"}");
  }

  getCurrentUser(): User{
    return JSON.parse(sessionStorage.getItem("user"));
  }
}
