import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {environment} from '../environments/environment';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthenticationService {
  constructor(private http: Http) { }
  login(username: string, password: string): Observable<string> {
    return this.http.get(environment.authenticationUrl).map(
      (res) => res.text()
    );
  }
}
