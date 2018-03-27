import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../models/User';
import { CurrentUserService } from '../../services/current-user.service';

@Component({
  selector: 'app-profile-header',
  templateUrl: './profile-header.component.html',
  styleUrls: ['./profile-header.component.css']
})
export class ProfileHeaderComponent implements OnInit {

  private leftTabPage;
  private middleTabPage;
  private allUsers: any = [];
  private usersForSearch: any = [];
  private textBoxPlaceholder: string = 'Loading...';
  private viewedUser = {};
  private hash: string;
  public s3: string = "https://s3.amazonaws.com/rev-grouptwo/images/";

  constructor(private client: HttpClient, private currUser: CurrentUserService) { 
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

  ngOnInit() {
    if(window.location.pathname.substring(8) == "" || window.location.pathname == '/updateInfo'){
      this.hash = this.currUser.getCurrentUser().imgHash;
      this.viewedUser = undefined;
    }

    this.client.get('http://localhost:8080/SocialMedia/allUsers', { withCredentials: true }).subscribe(
      (succ: any) => {
        this.allUsers = succ;
        console.log(this.allUsers);
        this.textBoxPlaceholder = "Search users"; //once it's done loading, change the placeholder text in the search bar
        console.log(window.location.pathname);
        if(window.location.pathname.substring(8) != ""){  //if there's more than just "/profile"
          for(var i=0; i<this.allUsers.length; i++){
            if(this.allUsers[i].username == window.location.pathname.substring(9)){
              this.viewedUser = this.allUsers[i];
              if(this.allUsers[i].hash != null){
                this.hash = this.allUsers[i].hash;
              }
            }
          }
        }
      },
    err => {
        alert('failed to retrieve user list');
    });
  }

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
    this.currUser.setUser(null);
  }

  filterSearch(){
    var input, filter, i;
    input = document.getElementById("searchbar");
    filter = input.value.toUpperCase();
    this.usersForSearch = []; //clear it from the last time this was run

    if(filter !== ""){
      for (i = 0; i < this.allUsers.length; i++) {
        if (this.allUsers[i].username.toUpperCase().indexOf(filter) == 0) { 
          //if the username starts with the input into the text field...
          this.usersForSearch.push(this.allUsers[i]);
        }
        else if (this.allUsers[i].first_name.toUpperCase().indexOf(filter) == 0) { 
          //if the first name starts with the input into the text field...
          this.usersForSearch.push(this.allUsers[i]);
        }
        else if (this.allUsers[i].last_name.toUpperCase().indexOf(filter) == 0) { 
          //if the last name starts with the input into the text field...
          this.usersForSearch.push(this.allUsers[i]);
        }
      }
      console.log(this.usersForSearch);
    }
  }

  routeForUser(username: string){
    window.location.pathname = "/profile/"+username;
  }
}
