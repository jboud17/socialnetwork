import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CurrentUserService } from '../../services/current-user.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private displayModal: boolean = false;

  constructor(private client: HttpClient, private router: Router, private currUser: CurrentUserService, private title: Title) { }

  ngOnInit() {
    this.title.setTitle('Home Page');
    this.client.get('http://localhost:8080/SocialMedia/session', { withCredentials: true }).subscribe(
      (succ: any) => {
        if(!succ.uid){  //if it's null
          this.router.navigate(['/login']);
          alert("You must be logged in to view this page");
        }
        else{
          sessionStorage.removeItem("user");
          
          const user = new User(succ.uid, succ.firstname, succ.lastname, succ.email, succ.imgHash);
          this.currUser.setUser(user);

          if(succ.imgHash == null){
            this.displayModal = true;
          }
           
          return succ;
        }
      },
    err => {
        alert('failed to retrieve sessionID');
    });
  }

  onClick() {
    document.getElementById("modal01").style.display = "block";
  }

  escPress(e: KeyboardEvent){
    console.log(e.keyCode);
    if(e.keyCode == 27){
      document.getElementById("modal01").style.display = 'none';
    }
  }


}
