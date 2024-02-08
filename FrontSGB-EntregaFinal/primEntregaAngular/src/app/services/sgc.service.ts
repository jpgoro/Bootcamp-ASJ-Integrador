import { Injectable } from '@angular/core';
import { Observable, from, map, of } from 'rxjs';
import { Proveedor}from '../models/proveedor'
import { HttpClient } from '@angular/common/http';
import { Country } from '../models/country';
import { ConditionIva } from '../models/condition-iva';
import { Industry } from '../models/industry';
import { Province } from '../models/province';
import { Supplier } from '../models/supplier';
import { Address } from '../models/address';
import { Contact } from '../models/contact';
import { Locality } from '../models/locality';



@Injectable({
  providedIn: 'root',
})
export class SgcService {
  private URL_API = 'http://localhost:8080/countries';
  private  URL_API1 = 'http://localhost:8080/iva';
  private  URL_API2 = 'http://localhost:8080/industries';
  private baseUrl = 'http://localhost:8080/provinces';
  private readonly URL_API3 = 'http://localhost:8080/suppliers';
  private URL_API4 = 'http://localhost:8080/address';
  private readonly URL_API5 = 'http://localhost:8080/contacts';
  private readonly URL_API6 = 'http://localhost:8080/localities'
  constructor(private http:HttpClient){this.loadProveedoresFromLocalStorage();}

  //Pais
  getAllCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(this.URL_API);
  }

  //Iva
  getIvaConditions(): Observable<ConditionIva[]> {
    return this.http.get<ConditionIva[]>(this.URL_API1);
  }

  //Rubro
  public getAllIndustries(): Observable<Industry[]> {
    return this.http.get<Industry[]>(this.URL_API2);
  }



  //Provincia
  getProvincesByCountryId (id:number): Observable<Province[]> {
    console.log(id)
    return this.http.get<Province[]>(`${this.baseUrl}/country/${id}`);

    //SUPPLIER
  }
  postSupplier(supplier: Supplier): Observable<Supplier> {
    return this.http.post<Supplier>(this.URL_API3, supplier);
  }

  getAllSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(this.URL_API3);
  }

  deleteSupplier(id: number): Observable<void> {
    return this.http.delete<void>(`${this.URL_API3}/${id}`);
  }

  getActiveSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.URL_API3}/active`);
  }
  putSupplier(id: number, supplier: Supplier): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.URL_API3}/${id}`, supplier);
  }
  getSupplierById(id: number): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.URL_API3}/${id}`);
  }

  //addres
  public postAddress(address: Address): Observable<Address> {
    return this.http.post<Address>(this.URL_API4, address);
  }
  public getAddressBySupplierId(id:number): Observable<Address[]> {
    return this.http.get<Address[]>(`${this.URL_API4}/suppliers/${id}`);
  }
  public putAddress(id: number, updatedAddress: Address): Observable<Address> {
    const url = `${this.URL_API4}/${id}`;
    return this.http.put<Address>(url, updatedAddress);
  }

  //contact
  public postContact(contact: Contact): Observable<Contact> {
    return this.http.post<Contact>(this.URL_API5, contact);
  }
  public getContactById(id: number): Observable<Contact> {
    const url = `${this.URL_API5}/${id}`;
    return this.http.get<Contact>(url);
  }

  public getAllContacts(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.URL_API5);
  }

  public getContactBySupplier(id: number): Observable<Contact[]> {
    const url = `${this.URL_API5}/suppliers/${id}`;
    return this.http.get<Contact[]>(url);
  }
  public putContact(id: number, updatedContact: Contact): Observable<Contact> {
    const url = `${this.URL_API5}/${id}`;
    return this.http.put<Contact>(url, updatedContact);
  }

  //locality
  public postLocality(locality: Locality): Observable<Locality> {
    return this.http.post<Locality>(this.URL_API6,locality);
  }
  public putLocality(id: number,locality: Locality): Observable<Locality> {
    return this.http.put<Locality>(this.URL_API6 + '/' + id,locality);
  }





//--------------------------------------------------------------
//Esto es lo viejo
  proveedores: Proveedor[] = [];

  private lastId: number = 0;


  datosProveedor: Proveedor = {
    id:-1,
    codigo:'',
    razon: '',
    rubro: '',
    email: '',
    direccion: '',
    cuit: '',
    iva:'',
    contacto: '',
    telefono: 0,
    url:'',
  };

  /* constructor(){
    this.loadProveedoresFromLocalStorage();
  } */
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
