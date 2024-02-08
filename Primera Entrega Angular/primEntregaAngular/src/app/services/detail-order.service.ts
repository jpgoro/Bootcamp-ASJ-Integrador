import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DetailOrder } from '../models/detail-order';

@Injectable({
  providedIn: 'root'
})
export class DetailOrderService {
  constructor(private http: HttpClient) { }
  private readonly URL_API = 'http://localhost:8080/details';

  public getAllDetailsOrders(): Observable<DetailOrder[]> {
    return this.http.get<DetailOrder[]>(this.URL_API);
  }

  public getDetailsOrderById(id: string): Observable<DetailOrder> {
    return this.http.get<DetailOrder>(`${this.URL_API}/${id}`);
  }

  public getDetailOrderByOrderId(pourchaseId: string): Observable<DetailOrder[]> {
    return this.http.get<DetailOrder[]>(`${this.URL_API}/order/${pourchaseId}`);
  }

  public postDetailOrder(orderDetail: DetailOrder[]): Observable<DetailOrder[]> {
    return this.http.post<DetailOrder[]>(this.URL_API, orderDetail);
  }

 /*  public updateOrderDetail(orderDetail: DetailOrder[]): Observable<DetailOrder[]> {
    return this.http.put<DetailOrder[]>(`${this.URL_API}/${orderDetail[0].id}`, orderDetail);
  } */

  public deleteDetailOrder(id: string): Observable<DetailOrder> {
    return this.http.delete<DetailOrder>(`${this.URL_API}/${id}`);
  }
}
