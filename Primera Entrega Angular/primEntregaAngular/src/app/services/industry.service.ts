import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Industry } from '../models/industry';

@Injectable({
  providedIn: 'root'
})
export class IndustryService {
  constructor(public http:HttpClient) {}
  private readonly URL_API = 'http://localhost:8080/industries'

  public getAllIndustries(): Observable<Industry[]> {
    return this.http.get<Industry[]>(this.URL_API);
  }

  public getIndustryById(id: string): Observable<Industry> {
    return this.http.get<Industry>(this.URL_API + '/' + id);
  }

  public getActiveIndustries(): Observable<Industry[]> {
    return this.http.get<Industry[]>(this.URL_API + '/active');
  }

  public getDeletedIndustries(): Observable<Industry[]> {
    return this.http.get<Industry[]>(this.URL_API + '/deleted');
  }

  public postIndustry(industry:Industry): Observable<Industry> {
    return this.http.post<Industry>(this.URL_API, industry);
  }

  public putIndustry(industry:Industry): Observable<Industry> {
    return this.http.put<Industry>(this.URL_API + '/' + industry.id, industry);
  }

  public undeleteIndustryById(id:string): Observable<Industry> {
    return this.http.patch<Industry>(this.URL_API + '/undelete/' + id, {});
  }

  public deleteIndustry(id:string): Observable<Industry> {
    return this.http.delete<Industry>(this.URL_API + '/' + id);
  }

}
