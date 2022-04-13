import { CovidInfo } from "../interfaces/CovidInfo";
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs";
import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
  })
export class TopSellingService {
  
    constructor(private http: HttpClient) { }
  
    getCountriesData() : Observable<CovidInfo[]> {
        console.log(">> all info of each country api request");
        return this.http.get<CovidInfo[]>(environment.API_URL+ "/info/top10");
    }
  

}