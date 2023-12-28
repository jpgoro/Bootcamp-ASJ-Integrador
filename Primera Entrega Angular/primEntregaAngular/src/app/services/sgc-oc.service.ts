import { Injectable } from '@angular/core';
import { Orden } from '../models/orden';
import { Observable, from, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SgcOcService {
  private lastId3: number = 0;
  ordenes: Orden[]= [];

  datosOrden: Orden = {
    id: -1,
  numeroOrden: 0,
  fechaEmision: '',
  fechaEntrega: '',
  direccion: '',
  proveedor_razon: '',
  proveedor_id: 0,
  nombreProducto:'',
  productoId: 0,
  cantidad:'',
  total:0,
  }

  constructor() {
    this.loadOrdenesFromLocalStorage();
   }

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
}
