import { Component, OnInit } from '@angular/core';
import { CovidInfo } from '../../../interfaces/CovidInfo';
import { TopSellingService } from '../../../services/top-selling.service';

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
  }


  getTop10(): void {
     this.service.getCountriesData().subscribe((info) => {
      this.all_countries_info = info;
    });
  }

  redirect(iso: string): void {
    window.location.href = "/info/" + iso;
  }


}
