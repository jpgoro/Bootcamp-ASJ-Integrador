import { Injectable } from '@angular/core';
import { Producto } from '../models/producto';
import { Proveedor}from '../models/proveedor'
import { Observable, from, of } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class SgcProdService {
  private lastId2: number = 0;
  productos: Producto[] = [];

  datosProducto: Producto = {
    id:-1,
    proveedor_id:0,//proveedor seleccionado de una lista de Tipo Proveedor
    proveedor_razon:'',
    sku: '',
    categoria: '',
    nombreProducto: '',
    descripcion: '',
    precio: ''
  }

  constructor() {
    this.loadProductosFromLocalStorage();
  }

  private loadProductosFromLocalStorage() {
    const storedProductos = localStorage.getItem('productos');
    if (storedProductos) {
      this.productos = JSON.parse(storedProductos);
      //busco ultimo id almacenado
      const lastProducts = this.productos[this.productos.length - 1];
      this.lastId2 = lastProducts ? lastProducts.id : 0;
    }
  }
  public createProd(producto: Producto): Observable<any> {
    this.lastId2++;
    producto.id = this.lastId2;
      this.productos.push(producto);
      localStorage.setItem('productos', JSON.stringify(this.productos));
      return of({ message: 'Producto creado exitosamente' });

    }
    public getPoductos(): Observable<Producto[]> {
      this.loadProductosFromLocalStorage();
      return of(this.productos);
    }
}
