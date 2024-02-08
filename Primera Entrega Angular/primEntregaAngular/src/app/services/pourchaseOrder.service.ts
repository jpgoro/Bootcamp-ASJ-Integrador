import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PurchaseOrder } from '../models/purchase-order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly URL_API = 'http://localhost:8080/purchaseOrder'
  constructor(private http: HttpClient) {}

  public getOrders(): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(this.URL_API);
  }

  public getOrdersActive(): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.URL_API}/active`);
  }

  public getOrdersDeleted(): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.URL_API}/deleted`);
  }

  public getOrderById(id:string): Observable<PurchaseOrder> {
    return this.http.get<PurchaseOrder>(`${this.URL_API}/${id}`);
  }

  public getOrdersByStatusId(status: string): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.URL_API}/status/${status}`);
  }

  public postOrder(order: PurchaseOrder): Observable<PurchaseOrder> {
    return this.http.post<PurchaseOrder>(this.URL_API, order);
  }

  public deleteOrder(id: string): Observable<PurchaseOrder> {
    return this.http.delete<PurchaseOrder>(`${this.URL_API}/${id}`);
  }

  public putOrder(id: string, order: PurchaseOrder): Observable<PurchaseOrder> {
    return this.http.put<PurchaseOrder>(`${this.URL_API}/${id}`, order);
  }

  public undeleteOrder(id: string): Observable<PurchaseOrder> {
    return this.http.patch<PurchaseOrder>(`${this.URL_API}/${id}/undelete`, true);
  }

}
