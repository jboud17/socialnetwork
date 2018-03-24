import { Injectable } from '@angular/core';
import { User } from '../models/User';

@Injectable()
export class ViewedUserService {
  constructor (){}

  private user: User;

  setUser(user: User){ 
    this.user = user;

    sessionStorage.setItem("viewedUser", JSON.stringify(this.user));

    // console.log("Viewed user set to: {id:"+this.user.id+",first name:"+this.user.firstName+",last name:"+this.user.lastName+",email:"+this.user.email+"}");
  }

  getCurrentUser(): User{
    return JSON.parse(sessionStorage.getItem("viewedUser"));
  }
}
