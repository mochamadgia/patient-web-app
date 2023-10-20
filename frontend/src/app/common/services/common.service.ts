import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry, timeout} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  BASE_URL: string;

  constructor(private http: HttpClient) {
    this.BASE_URL = 'http://localhost:5000/api';
  }

  post(url: string, body: any, reqOpts?: any, isNeedError?: boolean, maxRetry?: number, reqTimeout?: number): Observable<any> {

    if (this.checkConnection()) {

      return this.http.post<any>(this.BASE_URL + url, body, reqOpts)
      .pipe(
        timeout(reqTimeout ? reqTimeout : 300000),
        retry(maxRetry ? maxRetry : 0)
      );

    } else {
      console.error("error", "Network connection issues");
      return null as any;
    }

  }
  put(url: string, body: any, reqOpts?: any, isNeedError?: boolean, maxRetry?: number, reqTimeout?: number): Observable<any> {

    if (this.checkConnection()) {

      return this.http.put<any>(this.BASE_URL + url, body, reqOpts)
      .pipe(
        timeout(reqTimeout ? reqTimeout : 300000),
        retry(maxRetry ? maxRetry : 0)
      );

    } else {
      console.error("error", "Network connection issues");
      return null as any;
    }

  }

  delete(url: string, reqOpts?: any, isNeedError?: boolean, maxRetry?: number, reqTimeout?: number): Observable<any> {

    if (this.checkConnection()) {

      return this.http.delete<any>(this.BASE_URL + url, reqOpts)
      .pipe(
        timeout(reqTimeout ? reqTimeout : 300000),
        retry(maxRetry ? maxRetry : 0)
      );

    } else {
      console.error("error", "Network connection issues");
      return null as any;
    }

  }
  get(url: string, reqOpts?: any, isNeedError?: boolean, maxRetry?: number, reqTimeout?: number): Observable<any> {

    if (this.checkConnection()) {

      return this.http.get<any>(this.BASE_URL + url, reqOpts)
      .pipe(
        timeout(reqTimeout ? reqTimeout : 300000),
        retry(maxRetry ? maxRetry : 0)
      );

    } else {
      console.error("error", "Network connection issues");
      return null as any;
    }

  }

  checkConnection() {
    return navigator.onLine;
  }

}
