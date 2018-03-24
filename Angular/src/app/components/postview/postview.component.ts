import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CurrentUserService } from '../../services/current-user.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-postview',
  templateUrl: './postview.component.html',
  styleUrls: ['./postview.component.css']
})
export class PostviewComponent implements OnInit {

  constructor(
    private client: HttpClient, 
    private router: Router, 
    private currUser: CurrentUserService, 
    private title: Title) { }

  ngOnInit() {
    // this.title.setTitle('Profile Page');
    // this.client.get('http://localhost:8080/SocialMedia/allPosts', { withCredentials: true }).subscribe(
    //   (succ: any) => {
    //     console.log(succ);
    //   },
    // err => {
    //     alert('Error! Failed to load posts.');
    // });
  }

}
