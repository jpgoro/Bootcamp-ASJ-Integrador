import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { Producto } from '../../../models/producto';
import { SgcProdService } from '../../../services/sgc-prod.service';
import { Product } from '../../../models/product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tarj-producto',
  templateUrl: './tarj-producto.component.html',
  styleUrl: './tarj-producto.component.css',
})
export class TarjProductoComponent implements OnInit {
  productos: Product[] = [];
  constructor(public sgcProdService: SgcProdService, private router : Router) {}

  ngOnInit(): void {
    this.list();
  }

  list() {
    this.sgcProdService.getProductsDeleted().subscribe((res) => {
      this.productos = res;
    });
  }
  editarProducto(prodId: number) {
    this.router.navigate(['/producto/formAdd' + '/' + prodId]);
  }

  delete(id: number){
    let confirmacion = confirm("¿Desea eliminar el producto # "+id+"?");
    if(confirmacion){
      this.sgcProdService.deleteProduct(id).subscribe((res)=>{
        console.log("Eliminar")
        this.list();
      })
    }
  }
}
