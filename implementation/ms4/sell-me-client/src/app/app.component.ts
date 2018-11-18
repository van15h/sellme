import {Component, OnInit} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';

import {environment} from '../environments/environment';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  result: Observable<string>;

  isAuthenticated = false;

  constructor(private http: Http) {

  }

  ngOnInit(): void {
    this.result = this.http.get(environment.apiUrl).map(
      (res) => res.text()
    );
  }
}
