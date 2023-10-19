import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SelectivePreloadingStrategyService} from "./selective-preloading-strategy.service";

import {PatientComponent} from "./components/patient/patient.component";

const appRoutes: Routes = [
  {path: '', redirectTo: 'patient', pathMatch: 'full'},
  {path: 'patient', component: PatientComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {
        enableTracing: true,
        preloadingStrategy: SelectivePreloadingStrategyService,
        useHash: true,
        anchorScrolling: "enabled"
      }
    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/
