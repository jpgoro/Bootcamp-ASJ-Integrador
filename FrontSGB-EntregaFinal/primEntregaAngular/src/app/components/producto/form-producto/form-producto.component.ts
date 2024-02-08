import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SgcService } from '../../../services/sgc.service';
import { SgcProdService } from '../../../services/sgc-prod.service';
import { Proveedor } from '../../../models/proveedor';
import { ActivatedRoute, Router } from '@angular/router';
import { Industry } from '../../../models/industry';
import { ConditionIva } from '../../../models/condition-iva';
import { Supplier } from '../../../models/supplier';
import { Category } from '../../../models/category';
import { Product } from '../../../models/product';


@Component({
  selector: 'app-form-producto',
  templateUrl: './form-producto.component.html',
  styleUrl: './form-producto.component.css'
})
export class FormProductoComponent implements OnInit {
  proveedores: Supplier[] = [];
  categorias : Category[] = [];
  constructor(public sgcProdService : SgcProdService, public sgcService : SgcService, private router : Router, private route: ActivatedRoute){}


productViewModel: any = {
  id: 0,
  sku: '',
  name: '',
  description: '',
  image: '',
  price: 0,
  active: true,
  supplier: {id:0},
  category: {id:0},
};
editMode = false;


  ngOnInit(): void {
    this.sgcService.getAllSuppliers().subscribe((res)=>{
      this.proveedores = res;
    })
    this.sgcProdService.getCategory().subscribe((res)=>{
      this.categorias = res;
    })
    this.route.paramMap.subscribe((response) => {
      let id = response.get('id');
      if(id!==null){
        this.editMode = true;
        this.sgcProdService.getProductById(Number(id)).subscribe((res)=>{
          this.productViewModel.id = res.id;
          this.productViewModel.sku = res.sku;
          this.productViewModel.name = res.name;
          this.productViewModel.description = res.description;
          this.productViewModel.image = res.image;
          this.productViewModel.price = res.price;
          this.productViewModel.active = res.active;
          this.productViewModel.supplier.id = res.supplier.id;
          this.productViewModel.category.id = res.category.id;
        });
      }
    })



  }






  createProducto(form:NgForm){
    if(this.editMode){
      this.sgcProdService.putProduct(this.productViewModel.id, this.productViewModel).subscribe(res =>{
        console.log(res);
      })

    }else{
    this.sgcProdService.postProduct(this.productViewModel).subscribe((res)=>{
      console.log(res);
    })}


    /* else{
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
  } */

  }

}
