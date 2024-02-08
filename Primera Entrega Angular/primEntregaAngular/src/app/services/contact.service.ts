import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Contact } from '../models/contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private readonly URL_API = 'http://localhost:8080/contacts';

  constructor(private http: HttpClient) { }

  public getAllContacts(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.URL_API);
  }

  public getContactById(id: string): Observable<Contact> {
    const url = `${this.URL_API}/${id}`;
    return this.http.get<Contact>(url);
  }

  public getContactBySupplier(id: string): Observable<Contact[]> {
    const url = `${this.URL_API}/suppliers/${id}`;
    return this.http.get<Contact[]>(url);
  }

  public postContact(contact: Contact): Observable<Contact> {
    return this.http.post<Contact>(this.URL_API, contact);
  }

  public putContact(id: string, updatedContact: Contact): Observable<Contact> {
    const url = `${this.URL_API}/${id}`;
    return this.http.put<Contact>(url, updatedContact);
  }

  public deleteContact(id: string): Observable<void> {
    const url = `${this.URL_API}/${id}`;
    return this.http.delete<void>(url);
  }
}
