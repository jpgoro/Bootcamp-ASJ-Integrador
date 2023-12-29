import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SgcService } from '../../../services/sgc.service';
import { SgcProdService } from '../../../services/sgc-prod.service';
import { Proveedor } from '../../../models/proveedor';
import { Router } from '@angular/router';


@Component({
  selector: 'app-form-producto',
  templateUrl: './form-producto.component.html',
  styleUrl: './form-producto.component.css'
})
export class FormProductoComponent implements OnInit {
  proveedores: Proveedor[] = [];

  constructor(public sgcProdService : SgcProdService, public sgcService : SgcService, private router : Router){}

  ngOnInit(): void {
    this.sgcService.getProveedores().subscribe((res)=>{
      this.sgcService.getProveedores().subscribe((res)=>{
        this.proveedores = res;
      })

    })
  }

  createProducto(form:NgForm){

    if(form.value.id !=-1){
      this.sgcProdService.updateProducto(form.value).subscribe((res)=>{
        console.log(res);
        alert('Producto modificado correctamente');
      form.reset();
      this.sgcProdService.datosProducto.id = -1;
      // Recargar la página después de la operación
      this.router
      .navigateByUrl('/producto', { skipLocationChange: true })
      .then(() => {
        this.router.navigate(['/producto/tabla']);
      });
      })

    }else{
    this.sgcProdService.createProd(form.value).subscribe((res) =>{
      console.log(res);
      alert('Producto creado correctamente');
      form.reset();
      this.sgcProdService.datosProducto.id = -1;
      // Recargar la página después de la operación
      this.router
      .navigateByUrl('/producto', { skipLocationChange: true })
      .then(() => {
        this.router.navigate(['/producto/tabla']);
      });
    })
  }

  }

}
