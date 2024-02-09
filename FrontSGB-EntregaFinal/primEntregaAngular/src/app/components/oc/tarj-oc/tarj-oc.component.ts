import { Component, OnInit, ViewChild } from '@angular/core';
import { Orden } from '../../../models/orden';
import { SgcOcService } from '../../../services/sgc-oc.service';
import { PurchaseOrder } from '../../../models/purchase-order';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tarj-oc',
  templateUrl: './tarj-oc.component.html',
  styleUrl: './tarj-oc.component.css'
})
export class TarjOcComponent implements OnInit{
  ordenes : PurchaseOrder [] = [];

  constructor(public sgcOrdenService : SgcOcService,private router: Router,private route: ActivatedRoute){}

  ngOnInit(): void {
    this.list();
  }

  list(){
    this.sgcOrdenService.getOrders().subscribe((res)=>{
      this.ordenes = res;
      console.log(this.ordenes);
    })
  }

  cancelarOrden(id: number): void {
    this.sgcOrdenService.deleteOrder(id).subscribe((res) => {
      console.log(res);
      // Actualizar la lista de órdenes después de cancelar
      this.list();
    });
  }
  verOrden(id: number) {
    this.router.navigate(['orden/detalle/' + id]);
  }

}
