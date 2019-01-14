import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/do';

@Injectable()
export class AuthenticationService {
  private token = new BehaviorSubject(null);

  constructor(private http: Http) {
    const token = localStorage.getItem('token');

    if (token !== null) {
      this.token.next(token);
    }
  }

  login(username: string, password: string): Observable<string> {
    const params = {
      params: {
        email: username,
        password
      }
    };

    return this.http.get('http://localhost:8013/api/login', params).map(
      (res) => res.text()
    ).do((token) => this.token.next(token));
  }

  public getToken(): Observable<string> {
    return this.token;
  }
}
