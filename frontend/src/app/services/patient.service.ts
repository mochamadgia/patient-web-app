import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {CommonService} from "../common/services/common.service";
import {HttpParams} from "@angular/common/http";
import {Patient} from "../common/model/patient";


@Injectable({
  providedIn: 'root'
})
export class PatientService {
  observer = new Subject();
  observerList = new Subject();
  public subscriber$ = this.observer.asObservable();
  public subscriberList$ = this.observerList.asObservable();
  private patientUrl = '/patient';  // URL to web api
  constructor(private commonService: CommonService) {
  }

  getPatients(page: number, size: number): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page",page);
    queryParams = queryParams.append("size",size);
    return this.commonService.get(this.patientUrl, {params:queryParams});

  }
  create(data: Patient): Observable<any> {
    return this.commonService.post(this.patientUrl, data);
  }
  update(data: Patient): Observable<any> {
    return this.commonService.put(this.patientUrl, data);
  }

  delete(pid: number | undefined): Observable<any> {
    return this.commonService.delete(this.patientUrl+'/'+pid);
  }

  emitData(data?:Patient){
    this.observer.next(data);
  }

  emitDataToList(data?:object){
    this.observerList.next(data);
  }
  search(name:string, page: number, size: number): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("patientName",name);
    queryParams = queryParams.append("page",page);
    queryParams = queryParams.append("size",size);
    queryParams = queryParams.append("pid","");
    return this.commonService.get(this.patientUrl+'/search', {params:queryParams});

  }
}
