import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CurrentUserService } from '../../services/current-user.service';
import { Title } from '@angular/platform-browser';
import { User } from '../../models/User';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-postview',
  templateUrl: './postview.component.html',
  styleUrls: ['./postview.component.css']
})
export class PostviewComponent implements OnInit {

  private currentPath: string = "";
  private viewedUser;
  private postsToDisplay;
  private postUrl: string = 'http://localhost:8080/SocialMedia/likePost';
  public s3: string = "https://s3.amazonaws.com/rev-grouptwo/images/";
  public path: string = location.pathname;
  public u: User = null;
  private headerString: string = "";

  constructor(private client: HttpClient, private currUser: CurrentUserService) {}

  ngOnInit() {
    this.currentPath = window.location.pathname.substring(8);
      
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
              if(succ.length == 0){
                this.headerString = 'You have no posts to display.';
              }
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
              if(succ.length == 0){
                this.headerString = 'There are no posts in the database.';
              }
            }
          )
        }
      });
    }

    likeBtnClick(postId, userId) {

      console.log("postId: " + postId + " userId: " + userId);
      
      const myheader = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
      const postbody = new HttpParams().set('postId', postId).set('userId', userId);
      this.client.post(this.postUrl, postbody, { headers: myheader }).subscribe(
        (succ: any) => {
          var r = succ[0];
          if(r.status == "green") {
            //increase likes count in html
            var id = "likes-count-"+postId;
            var html = document.getElementById(id).innerHTML;
            var countLikes = parseInt(html);
            countLikes++;
            var str = String(countLikes);
            document.getElementById(id).textContent = str;

            //like icon animation
            var iconId = "thumb-up-"+postId;
            var el = document.getElementById(iconId);   
            var padding = 5;
            var interval = setInterval(iconAnimation, 100);
            var count = 0;
          }
            
          function iconAnimation() {
            count++;
            if (count < 5) {
              padding += .5; 
              el.style.padding = padding + 'px';
            } else if (count < 9) {
              padding -= .5; 
              el.style.padding = padding + 'px';
            } else {
              clearInterval(interval);
            }
          }

        },
        err => {
            alert('Error sending post request');
        }
      );
      
    }

}
