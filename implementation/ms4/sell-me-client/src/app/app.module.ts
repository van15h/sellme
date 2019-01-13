import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {CommonModule} from '@angular/common';
import {AuthenticationService} from './authentication.service';
import {AdvertisementService} from './advertisement.service';
import {AdvertisementListComponent} from './advertisement-list/advertisement-list.component';
import {TestService} from './services/test.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdvertisementListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    CommonModule,
    ReactiveFormsModule,
  ],
  providers: [
    AuthenticationService,
    AdvertisementService,
    TestService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
