import { NgModule } from '@angular/core';
import {PatientService} from "../services/patient.service";
import {CommonModule} from "@angular/common";
import {PatientListComponent} from "../components/patient-list/patient-list.component";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PatientListComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [PatientService],
  exports: [
    PatientListComponent
  ],
  bootstrap: []
})
export class PatientListModule { }
