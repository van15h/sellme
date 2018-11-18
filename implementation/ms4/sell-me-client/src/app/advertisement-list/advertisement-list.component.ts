import {Component, Input} from '@angular/core';
import {Advertisement} from '../model/advertisement.model';

@Component({
  selector: 'app-advertisement-list',
  templateUrl: './advertisement-list.component.html',
})
export class AdvertisementListComponent {
  @Input() advertisements: Advertisement[] = [];
}
