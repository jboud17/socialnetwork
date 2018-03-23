import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile-header',
  templateUrl: './profile-header.component.html',
  styleUrls: ['./profile-header.component.css']
})
export class ProfileHeaderComponent implements OnInit {

  private leftTabPage;
  private middleTabPage;

  constructor() { 
    if(window.location.pathname == '/profile') {
      this.leftTabPage = 'Home';
    } else if(window.location.pathname == '/home') {
      this.leftTabPage = 'Profile';
    }
    else{
      this.leftTabPage = 'Home';
    }

    if(window.location.pathname == '/updateInfo'){
      this.middleTabPage = 'Profile';
    }
    else{
      this.middleTabPage = 'Update Info'
    }
  }

  ngOnInit() {}

  leftTabClick(){
    if(window.location.pathname == '/profile'){
      window.location.pathname = '/home';
    }
    else if(window.location.pathname == '/home'){
      window.location.pathname = '/profile';
    }
    else{
      window.location.pathname = '/home';
    }
  }

  middleTabClick(){
    if(window.location.pathname == '/updateInfo'){
      window.location.pathname = '/profile';
    }
    else{
      window.location.pathname = '/updateInfo';
    }
  }

  logout(){
    window.location.href = 'http://localhost:8080/SocialMedia/logout';
  }
}
