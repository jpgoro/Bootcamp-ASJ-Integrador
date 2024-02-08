import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Address } from '../models/address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient) { }
  private URL_API = 'http://localhost:8080/addresses';
  public getAddresses(): Observable<Address[]> {
    return this.http.get<Address[]>(this.URL_API);
  }

  public getAddressById(id: string): Observable<Address> {
    const url = `${this.URL_API}/${id}`;
    return this.http.get<Address>(url);
  }

  public getAddressBySupplierId(id:string): Observable<Address[]> {
    return this.http.get<Address[]>(`${this.URL_API}/suppliers/${id}`);
  }

  public postAddress(address: Address): Observable<Address> {
    return this.http.post<Address>(this.URL_API, address);
  }

  public putAddress(id: string, updatedAddress: Address): Observable<Address> {
    const url = `${this.URL_API}/${id}`;
    return this.http.put<Address>(url, updatedAddress);
  }

  public deleteAddress(id: string): Observable<void> {
    const url = `${this.URL_API}/${id}`;
    return this.http.delete<void>(url);
  }
}
