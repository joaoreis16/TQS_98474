import { Component, OnInit } from '@angular/core';
import { CacheService } from '../services/cache.service';
import { Cache } from '../interfaces/Cache';

@Component({
  selector: 'app-cache',
  templateUrl: './cache.component.html',
  styleUrls: ['./cache.component.css']
})
export class CacheComponent implements OnInit {
  
  public cache : Cache | undefined;

  constructor( private service : CacheService) { 

    this.service.getCacheData().subscribe((info) => {
      this.cache = info;      
      this.cache.time_to_live = this.cache.time_to_live /1000;
    });


  }

  ngOnInit(): void {
  }

}
