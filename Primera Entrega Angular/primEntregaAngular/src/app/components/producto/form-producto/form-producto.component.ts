import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SgcService } from '../../../services/sgc.service';
import { SgcProdService } from '../../../services/sgc-prod.service';

@Component({
  selector: 'app-form-producto',
  templateUrl: './form-producto.component.html',
  styleUrl: './form-producto.component.css'
})
export class FormProductoComponent implements OnInit {

  constructor(public sgcProdService : SgcProdService, public sgcService : SgcService){}

  ngOnInit(): void {
    this.sgcService.getProveedores().subscribe((res)=>{

    })
  }

  createProducto(form:NgForm){
    if(form.value.id !=-1){
      /* this.sgcService.updateProducto(form.value).subscribe((res)=>{
        console.log(res);
        alert('Producto modificado correctamente');
      form.reset();
      }) */
      console.log("estas modificando");
    }else{
    this.sgcProdService.createProd(form.value).subscribe((res) =>{
      console.log(res);
      alert('Producto creado correctamente');
      form.reset();
    })
  }

  }

}
