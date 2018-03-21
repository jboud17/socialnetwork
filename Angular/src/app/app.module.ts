import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login-page/login.component';
import { PostviewComponent } from './components/postview/postview.component';
import { NewpostComponent } from './components/newpost/newpost.component';
import { SettingsComponent } from './components/settings/settings.component';
import { UpdateprofileComponent } from './components/updateprofile/updateprofile.component';
import { ProfilepageComponent } from './components/profile-page/profilepage.component';
import { HomeComponent } from './components/home-page/home.component';
import { ProfileHeaderComponent } from './components/profile-header/profile-header.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PostviewComponent,
    NewpostComponent,
    SettingsComponent,
    UpdateprofileComponent,
    ProfilepageComponent,
    HomeComponent,
    ProfileHeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login', component: LoginComponent},
      {path: 'home', component: HomeComponent},
      {path: 'profile', component: ProfilepageComponent},
      //{path: 'reimbursements', component: ReimbursementsAllComponent, canActivate: [LoggedInGuard, ManagerGuard]},
      {path: '**', pathMatch: 'full', redirectTo:'login'},
      {path: '', component: LoginComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
