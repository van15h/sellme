import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Advertisement} from './model/advertisement.model';
import {environment} from '../environments/environment';

@Injectable()
export class AdvertisementService {

  constructor(private http: Http) { }
  listAll(): Observable<Advertisement[]> {
    return this.http.get(environment.apiUrl + '/advertisements').map((res) => res.json());
  }
}

