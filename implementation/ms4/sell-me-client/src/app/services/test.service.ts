import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class TestService {

  constructor(private http: Http) { }

  public getTestIndex() {
    this.http.get("")
  }

}
