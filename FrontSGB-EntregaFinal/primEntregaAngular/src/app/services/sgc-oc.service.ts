import { Injectable } from '@angular/core';
import { Orden } from '../models/orden';
import { Observable, from, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { PurchaseOrder } from '../models/purchase-order';
import { DetailOrder } from '../models/detail-order';
import { Status } from '../models/status';

@Injectable({
  providedIn: 'root'
})
export class SgcOcService {

  private URL_API = 'http://localhost:8080/purchaseOrder'
  private URL_API2 = 'http://localhost:8080/details';
  private API_URL3 = 'http://localhost:8080/status';


  private lastId3: number = 0;
  ordenes: Orden[]= [];

  datosOrden: Orden = {
    id: -1,
  numeroOrden: 0,
  estado: true,
  fechaEmision: '',
  fechaEntrega: '',
  direccion: '',
  proveedor_razon: '',
  proveedor_id: 0,
  nombreProducto:'',
  productoId: 0,
  cantidad:'',
  total:0,
  cancelada:false
  }

  constructor(private http: HttpClient) {
    this.loadOrdenesFromLocalStorage();
   }

   //Orden de compra
   public getOrders(): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(this.URL_API);
  }

  public getOrdersActive(): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.URL_API}/active`);
  }

  public getOrdersDeleted(): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.URL_API}/deleted`);
  }

  public getOrderById(id:number): Observable<PurchaseOrder> {
    return this.http.get<PurchaseOrder>(`${this.URL_API}/${id}`);
  }

  public getOrdersByStatusId(status: number): Observable<PurchaseOrder[]> {
    return this.http.get<PurchaseOrder[]>(`${this.URL_API}/status/${status}`);
  }

  public postOrder(order: PurchaseOrder): Observable<PurchaseOrder> {
    return this.http.post<PurchaseOrder>(this.URL_API, order);
  }

  public deleteOrder(id: number): Observable<PurchaseOrder> {
    return this.http.delete<PurchaseOrder>(`${this.URL_API}/${id}`);
  }

  public putOrder(id: number, order: PurchaseOrder): Observable<PurchaseOrder> {
    return this.http.put<PurchaseOrder>(`${this.URL_API}/${id}`, order);
  }

  public undeleteOrder(id: number): Observable<PurchaseOrder> {
    return this.http.patch<PurchaseOrder>(`${this.URL_API}/${id}/undelete`, true);
  }



  //Detaller Orden de compra

  public getAllDetailsOrders(): Observable<DetailOrder[]> {
    return this.http.get<DetailOrder[]>(this.URL_API2);
  }

  public getDetailsOrderById(id: number): Observable<DetailOrder> {
    return this.http.get<DetailOrder>(`${this.URL_API2}/${id}`);
  }

  public getDetailOrderByOrderId(pourchaseId: number): Observable<DetailOrder[]> {
    return this.http.get<DetailOrder[]>(`${this.URL_API2}/order/${pourchaseId}`);
  }

  public postDetailOrder(orderDetail: DetailOrder[]): Observable<DetailOrder[]> {
    return this.http.post<DetailOrder[]>(this.URL_API2, orderDetail);
  }

 /*  public updateOrderDetail(orderDetail: DetailOrder[]): Observable<DetailOrder[]> {
    return this.http.put<DetailOrder[]>(`${this.URL_API2}/${orderDetail[0].id}`, orderDetail);
  } */

  public deleteDetailOrder(id: number): Observable<DetailOrder> {
    return this.http.delete<DetailOrder>(`${this.URL_API2}/${id}`);
  }

  //ESTADO

  public getStatus(): Observable<Status[]> {
    return this.http.get<Status[]>(this.API_URL3);
  }


























   //Viejoooooooooooooooooooooooooooooooooooooooooooo

  private loadOrdenesFromLocalStorage() {
    const storedOrdenes = localStorage.getItem('ordenes');
    if (storedOrdenes) {
      this.ordenes = JSON.parse(storedOrdenes);
      //busco ultimo id almacenado
      const lastOrdenes = this.ordenes[this.ordenes.length - 1];
      this.lastId3 = lastOrdenes ? lastOrdenes.id : 0;
    }
  }
  public createOrd(orden: Orden): Observable<any> {
    this.lastId3++;
    orden.id = this.lastId3;
      this.ordenes.push(orden);
      localStorage.setItem('ordenes', JSON.stringify(this.ordenes));
      return of({ message: 'Orden creada exitosamente' });

    }
    public getOrdenes(): Observable<Orden[]> {
      this.loadOrdenesFromLocalStorage();
      return of(this.ordenes);
    }

    public updateOrden(orden: Orden): Observable<any> {
      const index = this.ordenes.findIndex((p) => p.id === orden.id);
      if (index !== -1) {
        this.ordenes[index] = orden;
        localStorage.setItem('ordenes', JSON.stringify(this.ordenes));
        return of({ message: 'Orden actualizada exitosamente' });
      } else {
        return of({ error: 'Orden no encontrada' });
      }
    }

    public cancelarOrden(id: number): Observable<any> {
      const orden = this.ordenes.find((p) => p.id === id);
      if (orden) {
        orden.cancelada = true;
        localStorage.setItem('ordenes', JSON.stringify(this.ordenes));
        return of({ message: 'Orden cancelada exitosamente' });
      } else {
        return of({ error: 'Orden no encontrada' });
      }
    }
}
