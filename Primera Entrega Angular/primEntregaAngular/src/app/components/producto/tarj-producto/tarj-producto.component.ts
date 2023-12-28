import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { Producto } from '../../../models/producto';
import { SgcProdService } from '../../../services/sgc-prod.service';

@Component({
  selector: 'app-tarj-producto',
  templateUrl: './tarj-producto.component.html',
  styleUrl: './tarj-producto.component.css'
})
export class TarjProductoComponent implements OnInit {

  productos: Producto [] = [];
  constructor(public sgcProdService : SgcProdService){}

  ngOnInit(): void {
    this.list();
  }

  list(){
    this.sgcProdService.getPoductos().subscribe((res)=>{
      this.productos = res;
    })
  }
}
