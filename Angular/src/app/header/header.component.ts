import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CurrentUserService } from '../shared/current-user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private client: HttpClient, private currUser: CurrentUserService) { }

  ngOnInit() {
  }

  logout(){
    // sessionStorage.removeItem("user");
    // alert("You have logged out. Have a great day!");
    window.location.href="http://localhost:8080/Project1/logout";
  }
}
