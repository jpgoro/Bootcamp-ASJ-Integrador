import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { NgForm } from '@angular/forms';
import { Proveedor } from '../../../models/proveedor';

@Component({
  selector: 'app-tarj-proveedor',
  templateUrl: './tarj-proveedor.component.html',
  styleUrl: './tarj-proveedor.component.css',
})
export class TarjProveedorComponent implements OnInit{

  proveedores: Proveedor[] = [];
  constructor(public sgcService : SgcService){}

  ngOnInit(): void {
    this.list();
  }

  list(){
    this.sgcService.getProveedores().subscribe((res)=>{
      this.proveedores = res;
    })
  }

  editarProveedor(prov:Proveedor){
  this.sgcService.datosProveedor={
    id:prov.id,
    codigo:prov.codigo,
    razon: prov.razon,
    rubro: prov.rubro,
    email: prov.email,
    direccion: prov.direccion,
    cuit: prov.cuit,
    iva: prov.iva,
    contacto: prov.contacto,
    telefono: prov.telefono,
    url: prov.url
  };

  }
  delete(id: number){
    let confirmacion = confirm("¿Desea eliminar el usuario # "+id+"?");
    if(confirmacion){
      this.sgcService.deleteProveedor(id).subscribe((res)=>{
        console.log("Eliminar")
        this.list();
      })
    }
  }
}
