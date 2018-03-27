import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CurrentUserService } from '../../services/current-user.service';
import { Title } from '@angular/platform-browser';
import { User } from '../../models/User';

@Component({
  selector: 'app-postview',
  templateUrl: './postview.component.html',
  styleUrls: ['./postview.component.css']
})
export class PostviewComponent implements OnInit {

  private currentPath: string = "";
  private viewedUser;
  private postsToDisplay;
  public s3: string = "https://s3.amazonaws.com/rev-grouptwo/images/";
  public path: string = location.pathname;
  public u: User = null;
  private headerString: string = "";

  constructor(private client: HttpClient, private currUser: CurrentUserService) {}

  ngOnInit() {
    this.u = this.currUser.getCurrentUser();
    this.currentPath = window.location.pathname.substring(8);

    console.log(this.viewedUser);
      
    this.client.get('http://localhost:8080/SocialMedia/allUsers', { withCredentials: true }).subscribe(
      (succ: any) => {
        if(this.currentPath != ""){
          for(var i=0; i<succ.length; i++){
            if(succ[i].username == this.currentPath.substring(1)){
              var viewedUser = succ[i]; //not using this.viewedUser to not have to cast it
              this.viewedUser = succ[i];
              var u : User = new User(viewedUser.user_id, viewedUser.first_name, viewedUser.last_name, viewedUser.email, viewedUser.imgHash);

              this.client.get('http://localhost:8080/SocialMedia/postsById?'+u.id, { withCredentials: true }).subscribe(
                (succ: any) => {
                  console.log(succ);
                  this.postsToDisplay = succ;
                  if(succ.length == 0){
                    console.log('sup bitch');
                    this.headerString = 'This user has no posts to display.';
                  }
                },
                err => {
                    alert("Error loading that user's post list");
                });
            }
          }
        }
        else if(window.location.pathname == '/profile'){
          this.client.get('http://localhost:8080/SocialMedia/currUserPosts', { withCredentials: true }).subscribe(
            (succ: any) => {
              console.log(succ);
              this.postsToDisplay = succ;
              console.log(this.viewedUser);
            },
            err => {
                alert('Error loading your post list');
            });
        }
        else if(window.location.pathname == '/home'){
          this.client.get('http://localhost:8080/SocialMedia/allPosts', {withCredentials:true}).subscribe(
            (succ: any) => {
              console.log(succ);
              this.postsToDisplay = succ;
            }
          )
        }
      });
    }

    likeBtnClick(postId, userId) {
      console.log(postId + " " + userId);
    }

}
