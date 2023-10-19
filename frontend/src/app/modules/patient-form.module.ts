import { NgModule } from '@angular/core';
import {PatientFormComponent} from "../components/patient-form/patient-form.component";
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {PatientService} from "../services/patient.service";


@NgModule({
  declarations: [
    PatientFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  providers: [PatientService],
  exports: [
    PatientFormComponent
  ],
  bootstrap: []
})
export class PatientFormModule { }
