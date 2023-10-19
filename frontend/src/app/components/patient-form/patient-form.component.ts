import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {PatientService} from "../../services/patient.service";
import {Patient} from "../../common/model/patient";

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit{


  patientForm! : FormGroup ;
  patient?: Patient ;
  constructor(private fb: FormBuilder, private service: PatientService) {
  }
  ngOnInit(): void {
    this.patientForm = this.fb.group({
      pid: [null],
      firstName: [''],
      lastName: [''],
      dob: [''],
      gender: [''],
      phoneNo: [''],
      address: this.fb.group({
        address: [''],
        suburb: [''],
        state: [''],
        postCode: ['']
      })
    });

    this.service.subscriber$.subscribe(data => {
      this.patient = <Patient>data;

      this.patientForm.patchValue({
        pid: this.patient.pid,
        firstName: this.patient.firstName,
        lastName: this.patient.lastName,
        dob: this.patient.dob,
        gender: this.patient.gender,
        phoneNo: this.patient.phoneNo,
        address: {
          id:this.patient.address?.id,
          address:this.patient.address?.address,
          suburb:this.patient.address?.suburb,
          state:this.patient.address?.state,
          postCode:this.patient.address?.postCode,
        }
      });
    });
  }

  onSubmit() {
    if(this.patientForm.value.pid != null){
      this.service.update(this.patientForm.value).subscribe({
        next: () => this.patientForm.reset(),
        error: err => console.log(err)
      });
    }else{
      this.service.create(this.patientForm.value).subscribe({
        next: () => this.patientForm.reset(),
        error: err => console.log(err)
      });
    }
    this.service.emitDataToList();
  }

}
