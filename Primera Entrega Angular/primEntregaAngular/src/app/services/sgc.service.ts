import { Injectable } from '@angular/core';
import { Observable, from, of } from 'rxjs';
import { Proveedor}from '../models/proveedor'  



@Injectable({
  providedIn: 'root',
})
export class SgcService {
  proveedores: Proveedor[] = [];
  
  private lastId: number = 0;
  

  datosProveedor: Proveedor = {
    id:-1,
    codigo:0,
    razon: '',
    rubro: '',
    email: '',
    direccion: '',
    datosF: '',
    contacto: '',
  };
  
  /* constructor() { 
    const storedProveedores = localStorage.getItem('proveedores');
    if (storedProveedores) {
      this.proveedores = JSON.parse(storedProveedores);
    }
  }

  public createProv(proveedor: Proveedor): Observable<any> {
    this.proveedores.push(proveedor);
    localStorage.setItem('proveedores', JSON.stringify(this.proveedores));
    return of({message:'Proovedor creado exitosamente'});
  }

  public getProovedores(): Observable<Proveedor[]> {
    return of(this.proveedores);
  } */
  constructor(){
    this.loadProveedoresFromLocalStorage();
  }
  private loadProveedoresFromLocalStorage() {
    const storedProveedores = localStorage.getItem('proveedores');
    if (storedProveedores) {
      this.proveedores = JSON.parse(storedProveedores);
      //busco ultimo id almacenado
      const lastProvider = this.proveedores[this.proveedores.length - 1];
      this.lastId = lastProvider ? lastProvider.id : 0;
    }
  }
  

  public createProv(proveedor: Proveedor): Observable<any> {
    this.lastId++;
    proveedor.id = this.lastId;
      this.proveedores.push(proveedor);
      localStorage.setItem('proveedores', JSON.stringify(this.proveedores));
      return of({ message: 'Proveedor creado exitosamente' });
    
    }

    


    public getProveedores(): Observable<Proveedor[]> {
      this.loadProveedoresFromLocalStorage();
      return of(this.proveedores);
    }

    
    
    public updateProveedor(proveedor: Proveedor): Observable<any> {
      const index = this.proveedores.findIndex((p) => p.id === proveedor.id);
      if (index !== -1) {
        this.proveedores[index] = proveedor;
        localStorage.setItem('proveedores', JSON.stringify(this.proveedores));
        return of({ message: 'Proveedor actualizado exitosamente' });
      } else {
        return of({ error: 'Proveedor no encontrado' });
      }
    }

    public deleteProveedor(id: number): Observable<any> {
      // Filtrar el proveedor a eliminar
      const proveedorAEliminar = this.proveedores.find((p) => p.id === id);
    
      if (proveedorAEliminar) {
        // Filtrar la lista para quitar el proveedor
        this.proveedores = this.proveedores.filter((p) => p.id !== id);
    
        // Guardar la lista actualizada en el almacenamiento local
        localStorage.setItem('proveedores', JSON.stringify(this.proveedores));
    
        // Puedes retornar algún mensaje o información útil
        return of({ message: 'Proveedor eliminado exitosamente' });
      } else {
        // Si el proveedor no se encuentra, puedes retornar un mensaje de error o algo similar
        return of({ error: 'Proveedor no encontrado' });
      }
    }
  }
