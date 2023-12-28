import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SgcProdService } from '../../../services/sgc-prod.service';
import { SgcService } from '../../../services/sgc.service';
import { SgcOcService } from '../../../services/sgc-oc.service';
import { Proveedor } from '../../../models/proveedor';
import { Producto } from '../../../models/producto';

@Component({
  selector: 'app-form-oc',
  templateUrl: './form-oc.component.html',
  styleUrl: './form-oc.component.css'
})
export class FormOcComponent implements OnInit {
  proveedores: Proveedor[] = [];
  productos: Producto[] = [];
  constructor(public sgcProdService : SgcProdService, public sgcService : SgcService, public sgcOrdenService : SgcOcService){}


  ngOnInit(): void {
    this.sgcService.getProveedores().subscribe((res)=>{
      this.proveedores=res;

    })

    this.sgcProdService.getPoductos().subscribe((res)=>{
      this.productos = res;

    })

  }

  createOrden(form:NgForm){
    console.log(form.value)
    if(form.value.id !=-1){
      /* this.sgcProdService.updateProducto(form.value).subscribe((res)=>{
        console.log(res);
        alert('Producto modificado correctamente');
      form.reset();
      }) */
      console.log('modificando')
    }else{
    this.sgcOrdenService.createOrd(form.value).subscribe((res) =>{
      console.log(res);
      alert('Orden creada correctamente');
      form.reset();
    })
  }

  }

}
