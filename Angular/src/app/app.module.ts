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
import { RegisterComponent } from './components/register-page/register.component';
import { LoggedInGuard } from './logged-in-guard/logged-in.guard';
import { CurrentUserService } from './services/current-user.service';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';


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
    ProfileHeaderComponent,
    RegisterComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'register', component: RegisterComponent},
      {path: 'login', component: LoginComponent},
      {path: 'home', component: HomeComponent},
      {path: 'profile', component: ProfilepageComponent},
      //{path: 'home', component: HomeComponent, canActivate: [LoggedInGuard]},
      //{path: 'profile', component: ProfilepageComponent, canActivate: [LoggedInGuard]},
      {path: '**', pathMatch: 'full', component: PageNotFoundComponent},
      {path: '', component: LoginComponent}
    ])
  ],
  providers: [
    LoggedInGuard,
    CurrentUserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
