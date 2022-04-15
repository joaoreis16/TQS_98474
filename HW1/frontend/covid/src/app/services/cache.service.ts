import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs";
import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { Cache } from '../interfaces/Cache';

@Injectable({
    providedIn: 'root',
  })
export class CacheService {
  
    constructor(private http: HttpClient) { }
  
    getCacheData() : Observable<Cache> {
        console.log(">> cache api request ");
        return this.http.get<Cache>(environment.API_URL + '/info/cache');
    }

}