import { Component } from '@angular/core';
import {Product,TopSelling, TableRows, Employee} from './static-data';


@Component({
    selector: 'app-table',
    templateUrl: 'country.component.html'
})
export class CountryComponent {
  topSelling:Product[];

  trow:TableRows[];

  constructor() { 

    this.topSelling=TopSelling;

    this.trow=Employee;
  }
}
