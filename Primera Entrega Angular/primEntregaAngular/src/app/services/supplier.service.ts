import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Supplier } from '../models/supplier';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {
  private readonly baseUrl = 'http://localhost:8080/suppliers';

  constructor(private http: HttpClient) {}

  getAllSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(this.baseUrl);
  }

  getSupplierById(id: string): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.baseUrl}/${id}`);
  }

  getActiveSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/active`);
  }

  getDeletedSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/deleted`);
  }

  getSuppliersByLegalNameAsc(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/businessNameAsc`);
  }

  getSuppliersByLegalNameDesc(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/businessNameDesc`);
  }

  postSupplier(supplier: Supplier): Observable<Supplier> {
    return this.http.post<Supplier>(this.baseUrl, supplier);
  }

  putSupplier(id: string, supplier: Supplier): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.baseUrl}/${id}`, supplier);
  }

  patchSupplier(id: string): Observable<Supplier> {
    return this.http.patch<Supplier>(`${this.baseUrl}/${id}/undelete`, true);
  }

  deleteSupplier(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

}
