import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Locality } from '../models/locality';

@Injectable({
  providedIn: 'root'
})
export class LocalityService {
  private readonly URL_API = 'http://localhost:8080/localities';
  constructor(private http: HttpClient) { }

  public getLocalities(): Observable<Locality[]> {
    return this.http.get<Locality[]>(this.URL_API);
  }

  public postLocality(locality: Locality): Observable<Locality> {
    return this.http.post<Locality>(this.URL_API,locality);
  }

  public putLocality(id: string,locality: Locality): Observable<Locality> {
    return this.http.put<Locality>(this.URL_API + '/' + id,locality);
  }
}
