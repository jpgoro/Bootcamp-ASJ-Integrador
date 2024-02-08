import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ConditionIva } from '../models/condition-iva';

@Injectable({
  providedIn: 'root'
})
export class ConditionIvaService {
  private readonly URL_API = 'http://localhost:8080/iva';
  constructor(private http: HttpClient) { }

  getIvaConditions(): Observable<ConditionIva[]> {
    return this.http.get<ConditionIva[]>(this.URL_API);
  }
}
