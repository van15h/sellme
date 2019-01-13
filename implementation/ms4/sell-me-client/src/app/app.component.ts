import {Component, OnInit} from '@angular/core';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';
import {Advertisement} from './model/advertisement.model';
import {AdvertisementService} from './advertisement.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  advertisements: Observable<Advertisement[]>;

  isAuthenticated = false;

  constructor(private advertisementService: AdvertisementService) {

  }

  ngOnInit(): void {
    // this.advertisements = this.advertisementService.listAll();
  }
}
