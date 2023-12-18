import { Component } from '@angular/core';

@Component({
  selector: 'app-tarj-proveedor',
  templateUrl: './tarj-proveedor.component.html',
  styleUrl: './tarj-proveedor.component.css',
})
export class TarjProveedorComponent {
  id: string = '';
  razonSocial: string = '';
  rubro: string = '';
  email: string = '';
  direccion: string = '';
  datosFiscales: string = '';
  contacto: string = '';

  proveedores = [
    {
      id: 'pepe1',
      razonSocial: 'fafasf',
      rubro: 'fasdfas',
      email: 'fasdfsa',
      direccion: 'fasdfasdf',
      datosFiscales: 'fsadfsa',
      contacto: 'fasdfsdfsd'
    },
    {
      id: 'pepe1',
      razonSocial: 'fafasf',
      rubro: 'fasdfas',
      email: 'fasdfsa',
      direccion: 'fasdfasdf',
      datosFiscales: 'fsadfsa',
      contacto: 'fasdfsdfsd'
    },
    {
      id: 'pepe1',
      razonSocial: 'fafasf',
      rubro: 'fasdfas',
      email: 'fasdfsa',
      direccion: 'fasdfasdf',
      datosFiscales: 'fsadfsa',
      contacto: 'fasdfsdfsd'
    },
    {
      id: 'pepe1',
      razonSocial: 'fafasf',
      rubro: 'fasdfas',
      email: 'fasdfsa',
      direccion: 'fasdfasdf',
      datosFiscales: 'fsadfsa',
      contacto: 'fasdfsdfsd'
    },
  ];
}
