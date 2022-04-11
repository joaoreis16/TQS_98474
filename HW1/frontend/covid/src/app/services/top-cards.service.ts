import { CovidInfo } from "../interfaces/CovidInfo";
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs";
import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
  })
export class TopCardsService {

    private info!: CovidInfo;
  
    constructor(private http: HttpClient) { }
  
    getWorldData() : Observable<CovidInfo> {
        console.log(">> world data api request ");
        return this.http.get<CovidInfo>(environment.API_URL + '/info/world');
    }
  

}