import { CovidInfo } from "../interfaces/CovidInfo";
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs";
import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { LastSixMonths } from "../interfaces/LastSixMonths";

@Injectable({
    providedIn: 'root',
  })
export class CountryService {
  
    constructor(private http: HttpClient) { }
  
    getLastSixMonths(iso : string) : Observable<LastSixMonths[]> {
        console.log(">> last six months data api request ");
        return this.http.get<LastSixMonths[]>(environment.API_URL + '/info/last6months/'+ iso);
    }
  
    getCountry(iso : string) : Observable<CovidInfo> {
        console.log(">> country data api request ");
        return this.http.get<CovidInfo>(environment.API_URL + '/info/'+ iso);
    }

    getISObyName(countyname: string) : Observable<string> {
        return this.http.get<string>(environment.API_URL + '/info/iso/'+ countyname);
    }

}