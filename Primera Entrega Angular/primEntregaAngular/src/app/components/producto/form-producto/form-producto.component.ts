import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SgcService } from '../../../services/sgc.service';
import { SgcProdService } from '../../../services/sgc-prod.service';
import { Proveedor } from '../../../models/proveedor';

@Component({
  selector: 'app-form-producto',
  templateUrl: './form-producto.component.html',
  styleUrl: './form-producto.component.css'
})
export class FormProductoComponent implements OnInit {
  proveedores: Proveedor[] = [];

  constructor(public sgcProdService : SgcProdService, public sgcService : SgcService){}

  ngOnInit(): void {
    this.sgcService.getProveedores().subscribe((res)=>{
      this.sgcService.getProveedores().subscribe((res)=>{
        this.proveedores = res;
      })

    })
  }

  createProducto(form:NgForm){
    console.log(form.value)
    if(form.value.id !=-1){
      this.sgcProdService.updateProducto(form.value).subscribe((res)=>{
        console.log(res);
        alert('Producto modificado correctamente');
      form.reset();
      })
      
    }else{
    this.sgcProdService.createProd(form.value).subscribe((res) =>{
      console.log(res);
      alert('Producto creado correctamente');
      form.reset();
    })
  }

  }

}
