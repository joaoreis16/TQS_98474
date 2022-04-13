import { Component, OnInit, ViewChild } from '@angular/core';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexDataLabels,
  ApexTooltip,
  ApexStroke
} from "ng-apexcharts";

import { CovidInfo } from '../../interfaces/CovidInfo';
import { LastSixMonths } from '../../interfaces/LastSixMonths';
import { CountryService } from '../../services/country.service';
import { Router } from '@angular/router';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  stroke: ApexStroke;
  tooltip: ApexTooltip;
  dataLabels: ApexDataLabels;
  colors: string[];
};



@Component({
    selector: 'app-graph',
    templateUrl: 'country.component.html'
})
export class CountryComponent implements OnInit {

  public not_equal : boolean = false; 
  public not_found : boolean = false; 
  public stats : LastSixMonths[] = [];
  public info_country : CovidInfo | undefined;
  public iso : string = "";

  public flag_cases : boolean = true;
  public flag_death : boolean = false;
  public flag_test : boolean = false;

  @ViewChild("chart") chart: ChartComponent = Object.create(null);
  public new_cases_graph: Partial<ChartOptions> = Object.create(null);
  public total_cases_graph: Partial<ChartOptions> = Object.create(null);
  public new_death_graph: Partial<ChartOptions> = Object.create(null);
  public total_death_graph: Partial<ChartOptions> = Object.create(null);
  public new_tests_graph: Partial<ChartOptions> = Object.create(null);
  public total_tests_graph: Partial<ChartOptions> = Object.create(null);

  constructor(private service: CountryService, private router: Router) {
  }


  ngOnInit(): void {
    const url_array = this.router.url.split("/");
    const last = url_array[url_array.length - 1];

    if (last != "info") {
      this.iso = last;
    }

    if (this.iso != "") {
      this.getCountry();
    }

  }


  getLastSixMonths(iso: string): void {
     this.service.getLastSixMonths(iso).subscribe((info) => {
      this.stats = info;
      
      var x = [];
      var new_cases = [];
      var total_cases = [];
      var new_death = [];
      var total_death = [];
      var new_tests = [];
      var total_tests = [];

      for (var val of this.stats)  {
        x.push(val.date);
        new_cases.push(val.new_cases);
        total_cases.push(val.total_cases);
        new_death.push(val.new_deaths);
        total_death.push(val.total_deaths);
        new_tests.push(val.new_tests);
        total_tests.push(val.total_tests);
      }

      this.new_cases_graph = { series: [ {name: "New Cases", data: new_cases }, ], chart: {height: 350,type: "area"}, dataLabels: { enabled: false }, stroke: { curve: "smooth" }, xaxis: {type: "datetime", categories: x }, tooltip: { x: {  format: "dd/MM/yy" } } };
      this.total_cases_graph = { series: [ {name: "Total Cases", data: total_cases }, ], chart: {height: 350,type: "area"}, dataLabels: { enabled: false }, stroke: { curve: "smooth" }, xaxis: {type: "datetime", categories: x }, tooltip: { x: {  format: "dd/MM/yy" } } };
      this.new_death_graph = { series: [ {name: "New Deaths", data: new_death }, ], chart: {height: 350,type: "area"}, dataLabels: { enabled: false }, stroke: { curve: "smooth" }, xaxis: {type: "datetime", categories: x }, tooltip: { x: {  format: "dd/MM/yy" } }, colors : ["#DC143C"] };
      this.total_death_graph = { series: [ {name: "Total Deaths", data: total_death }, ], chart: {height: 350,type: "area"}, dataLabels: { enabled: false }, stroke: { curve: "smooth" }, xaxis: {type: "datetime", categories: x }, tooltip: { x: {  format: "dd/MM/yy" } }, colors : ["#DC143C"]  };
      this.new_tests_graph = { series: [ {name: "New Tests", data: new_tests }, ], chart: {height: 350,type: "area"}, dataLabels: { enabled: false }, stroke: { curve: "smooth" }, xaxis: {type: "datetime", categories: x }, tooltip: { x: {  format: "dd/MM/yy" } }, colors : ["#3CB371"] };
      this.total_tests_graph = { series: [ {name: "Total Tests", data: total_tests }, ], chart: {height: 350,type: "area"}, dataLabels: { enabled: false }, stroke: { curve: "smooth" }, xaxis: {type: "datetime", categories: x }, tooltip: { x: {  format: "dd/MM/yy" } }, colors : ["#3CB371"]  };


    });

  }


  getCountry() : void {

    /* if (this.iso == undefined) {
      this.iso = (<HTMLInputElement>document.getElementById("form1")).value.toLowerCase(); 
    } */

    this.service.getCountry(this.iso).subscribe((info) => {
      if (info.country == null) {
        // significa que o utilizador colocou um iso code que não existe
        this.not_found = true
        this.info_country = undefined
        this.stats = [];

      } else {
        this.not_found = false
        this.info_country = info;
      }
      
    });


    this.getLastSixMonths(this.iso)
  }



  search() : void {

    var iso = (<HTMLInputElement>document.getElementById("form1")).value;
    var countryname = (<HTMLInputElement>document.getElementById("form2")).value;

    if ( iso == "" && countryname == "" ) {
      this.not_found = true
      this.info_country = undefined
      this.stats = [];

    } else if ( countryname == "" ) {
      window.location.href = "/info/" + iso.toLowerCase();

    } else if (iso == "" ) {
      // get iso by countyname
      this.service.getISObyName(countryname.toLowerCase()).subscribe((info) => {
        var data = JSON.parse( JSON.stringify(info) );

        if (data == null) {
          this.not_found = true;
          this.info_country = undefined;
          this.stats = [];

        } else {
          window.location.href = "/info/" + data.iso.toLowerCase();
        }
      });

    } else {
      // verificar se o iso coincide com o countryname
      this.service.getISObyName(countryname.toLowerCase()).subscribe((info) => {
        var data = JSON.parse( JSON.stringify(info) );

        if (data == null) {
          this.not_found = true;
          this.info_country = undefined;
          this.stats = [];

        } else if (iso != data.iso) {
          this.not_equal = true;
          this.info_country = undefined;
          this.stats = [];

        } else {
          this.not_equal = false;
          window.location.href = "/info/" + data.iso.toLowerCase();
        }
      });

    }
    
  }

  appear(type : string): void {

    switch(type) { 
      case "cases": { 
         this.flag_cases = true;
         this.flag_death = false;
         this.flag_test = false;
         break; 
      } 
      case "death": { 
        this.flag_cases = false;
        this.flag_death = true;
        this.flag_test = false; 
         break; 
      } 
      case "tests": { 
        this.flag_cases = false;
        this.flag_death = false;
        this.flag_test = true; 
        break; 
     } 
   } 
  }

}
