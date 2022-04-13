import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';

export const Approutes: Routes = [
  {
    path: '',
    component: FullComponent,
    children: [
      { path: '', redirectTo: '/world', pathMatch: 'full' },
      {
        path: 'world',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'info',
        loadChildren: () => import('./component/component.module').then(m => m.ComponentsModule)
      }
    ]
  }
];
