import { Component, OnInit } from '@angular/core';
import { SgcOcService } from '../../../services/sgc-oc.service';
import { PurchaseOrder } from '../../../models/purchase-order';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailOrder } from '../../../models/detail-order';

@Component({
  selector: 'app-detalle-oc',
  templateUrl: './detalle-oc.component.html',
  styleUrl: './detalle-oc.component.css'
})

export class DetalleOcComponent implements OnInit {
  orden : PurchaseOrder ={
    number:"",
    issueDate: "",
    deliveryDate: "",
    active: true,
    reception: "",
    supplier: {},
    status:{}
  };
  arrayDetalles : DetailOrder [] = [];
  auxDetailOrder : DetailOrder={
    quantity: 0,
    price: 0,
    product: {
    },
    purchaseOrder: {},
  }
  idOrden:number = 0;
  constructor(public sgcOrdenService : SgcOcService,private router: Router,private route: ActivatedRoute){}

  ngOnInit(): void {

    this.route.paramMap.subscribe((response) => {
      this.idOrden = Number(response.get('id'))
      this.list();
      this.obtenerDetalle();
    });

  }

  list(){
    this.sgcOrdenService.getOrderById(this.idOrden).subscribe((res)=>{
      this.orden = res;
      console.log(this.orden);
    })
  }

  cancelarOrden(id: number): void {
    this.sgcOrdenService.deleteOrder(id).subscribe((res) => {
      console.log(res);
      // Actualizar la lista de órdenes después de cancelar
      this.list();
    });
  }

  obtenerDetalle(){
    this.sgcOrdenService.getDetailOrderByOrderId(this.idOrden).subscribe((res)=>{
      this.arrayDetalles = res;
      console.log(this.arrayDetalles);
    })

  }

  calcularTotal():number{
    let total =0;
    for(let detalle of this.arrayDetalles){
      total+=detalle.price*detalle.quantity;
  }
  return total;
}
}
