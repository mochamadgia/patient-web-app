import {Component, OnInit} from '@angular/core';
import {PatientService} from "../../services/patient.service";
import {Patient} from "../../common/model/patient";
import {Page} from "../../common/model/page";
import {PatientFormComponent} from "../patient-form/patient-form.component";


@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients?: Patient[];
  currentPatient: Patient = new Patient();
  currentIndex:number= 0;
  page: Page = {
    size: 10,
    number: 0,
    totalPages: 0,
    totalElements: 0,
    numberOfElements: 0,
    first: true,
    last: false
  };
  onEdit(patient: Patient) {
    this.service.emitData(patient);
  }
  getStartOfElement(){
    return (Number(this.page.size)*(Number(this.page.number))+1);
  }
  getEndOfElement(){
    return Number(this.page.size)*(Number(this.page.number)+1);
  }

  onDelete(patient: Patient) {
    this.setActiveTutorial(patient);
    this.service.delete(this.currentPatient.pid).subscribe({
      next: () => this.getAll(this.currentIndex),
      error: err => console.error(err)
    })
  }

  setActiveTutorial(patient: Patient): void {
    console.log(patient);
    this.currentPatient = patient;
  }

  getAll(index:number): void {
    this.service.getPatients(index, 10).subscribe({
      next: value => {
        this.patients = value.data.content
        this.page.number = value.data.number;
        this.page.size = value.data.size;
        this.page.first= value.data.first;
        this.page.last = value.data.last;
        this.page.totalPages = value.data.totalPages;
        this.page.totalElements = value.data.totalElements;
        this.page.numberOfElements = value.data.numberOfElements;
        this.currentIndex = value.data.number;
        console.log(this.page);
      },
      error: err => console.log(err),
      complete: () => {
      }
    })
  }

  nextSearch(){
    this.getAll(this.currentIndex+1);
  }
  previousSearch(){
    this.getAll(this.currentIndex-1);
  }
  ngOnInit(): void {
    this.page = {
      size: 10,
      number: 0,
      totalPages: 0,
      totalElements: 0,
      numberOfElements: 0,
      first: true,
      last: false
    };
    this.getAll(this.currentIndex);
    this.service.subscriberList$.subscribe(value => {
      this.getAll(this.currentIndex);
    })
  }

  constructor(private service: PatientService, private _parent: PatientFormComponent) {
  }

  onSearch($event: any) {
    console.log($event);
    this.service.search($event.target.value, 0, 10).subscribe({
      next: value => this.patients = value.data.content,
      error: err => console.log(err),
      complete: () => {
      }
    })
  }
}
