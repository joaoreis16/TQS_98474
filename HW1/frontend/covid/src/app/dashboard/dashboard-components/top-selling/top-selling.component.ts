import { Component, OnInit } from '@angular/core';
import { CovidInfo } from '../../../interfaces/CovidInfo';
import { TopSellingService } from '../../../services/top-selling.service';
import {Product,TopSelling} from './top-selling-data';

@Component({
  selector: 'app-top-selling',
  templateUrl: './top-selling.component.html'
})
export class TopSellingComponent implements OnInit {

  public all_countries_info: CovidInfo[] = [];

  constructor(public service: TopSellingService) { 
  }

  ngOnInit(): void {
    this.getTop10();
    console.log("AQUI 3 "+ this.all_countries_info);
   
  }


  getTop10(): void {
     this.service.getCountriesData().subscribe((info) => {
      this.all_countries_info = info;
      console.log("AQUI 2 "+ JSON.stringify(this.all_countries_info));
    });

    console.log("AQUI 1 "+ this.all_countries_info);
  }


}
