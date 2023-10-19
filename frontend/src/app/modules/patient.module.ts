import { NgModule } from '@angular/core';
import {CommonModule} from "@angular/common";
import {PatientListModule} from "./patient-list.module";
import {PatientComponent} from "../components/patient/patient.component";
import {PatientFormComponent} from "../components/patient-form/patient-form.component";
import {PatientFormModule} from "./patient-form.module";


@NgModule({
  declarations: [
    PatientComponent
  ],
  imports: [
    CommonModule,
    PatientListModule,
    PatientFormModule
  ],
  providers: [PatientFormComponent],
  bootstrap: []
})
export class PatientModule { }
