import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { Producto } from '../../../models/producto';
import { SgcProdService } from '../../../services/sgc-prod.service';

@Component({
  selector: 'app-tarj-producto',
  templateUrl: './tarj-producto.component.html',
  styleUrl: './tarj-producto.component.css',
})
export class TarjProductoComponent implements OnInit {
  productos: Producto[] = [];
  constructor(public sgcProdService: SgcProdService) {}

  ngOnInit(): void {
    this.list();
  }

  list() {
    this.sgcProdService.getPoductos().subscribe((res) => {
      this.productos = res;
    });
  }
  editarProducto(prod: Producto) {
    this.sgcProdService.datosProducto = {
      id: prod.id,
      proveedor_id: prod.proveedor_id,
      proveedor_razon: prod.proveedor_razon,
      sku: prod.sku,
      categoria: prod.categoria,
      nombreProducto: prod.nombreProducto,
      descripcion: prod.descripcion,
      precio: prod.precio,
    };
  }

  delete(id: number){
    let confirmacion = confirm("¿Desea eliminar el producto # "+id+"?");
    if(confirmacion){
      this.sgcProdService.deleteProducto(id).subscribe((res)=>{
        console.log("Eliminar")
        this.list();
      })
    }
  }
}
