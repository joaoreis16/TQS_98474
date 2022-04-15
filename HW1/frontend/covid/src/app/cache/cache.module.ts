import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgApexchartsModule } from 'ng-apexcharts';
import { CacheComponent } from './cache.component';

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Cache",
      urls: [{ title: "Cache", url: "/cache" }, { title: "Cache" }],
    },
    component: CacheComponent,
  },
];


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    NgApexchartsModule,
  ],
  declarations: [
    CacheComponent
  ]
})
export class CacheModule { }
