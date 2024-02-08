import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { NgForm } from '@angular/forms';
import { Proveedor } from '../../../models/proveedor';
import { Supplier } from '../../../models/supplier';
import { Contact } from '../../../models/contact';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tarj-proveedor',
  templateUrl: './tarj-proveedor.component.html',
  styleUrl: './tarj-proveedor.component.css',
})
export class TarjProveedorComponent implements OnInit{

  proveedores: Supplier[] = [];
  constructor(public sgcService : SgcService, private router : Router){}

  ngOnInit(): void {
    this.list();
  }

  list(){

    this.sgcService.getAllSuppliers().subscribe((res)=>{
      this.proveedores=res;
      this.proveedores.forEach(proveedor => {
        this.sgcService.getContactBySupplier(proveedor.id).subscribe(res=>{
          proveedor.contact = res;
          console.log(proveedor.contact);
        })
      });

    })

  }



  editarProveedor(prov:Supplier) {
    const idProveedor = prov?.id;
    this.router.navigate(['/proveedor/formAdd' + '/' + idProveedor]);
  }


  delete(id: number){
    let confirmacion = confirm("¿Desea eliminar el usuario # "+id+"?");
    if(confirmacion){
      this.sgcService.deleteSupplier(id).subscribe((res)=>{
        console.log("Eliminar")
        this.list();
      })
    }
  }
}
