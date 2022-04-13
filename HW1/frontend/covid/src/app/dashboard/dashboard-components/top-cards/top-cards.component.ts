import { Component, OnInit } from '@angular/core';
import { topcard, topcards } from './top-cards-data';
import { TopCardsService } from '../../../services/top-cards.service';
import { CovidInfo } from '../../../interfaces/CovidInfo';

@Component({
  selector: 'app-top-cards',
  templateUrl: './top-cards.component.html'
})
export class TopCardsComponent implements OnInit {

  topcards!:topcard[];
  public world_info: CovidInfo | undefined;

  constructor(public service: TopCardsService) { 
  }

  ngOnInit(): void {
    this.getWorldData();
   
  }


  getWorldData(): void {
     this.service.getWorldData().subscribe((info) => {
      this.world_info = info;
    });

  }

}
