import { Component, OnInit, ViewChild } from '@angular/core';
import { Orden } from '../../../models/orden';
import { SgcOcService } from '../../../services/sgc-oc.service';

@Component({
  selector: 'app-tarj-oc',
  templateUrl: './tarj-oc.component.html',
  styleUrl: './tarj-oc.component.css'
})
export class TarjOcComponent implements OnInit{
  ordenes : Orden [] = [];

  constructor(public sgcOrdenService : SgcOcService){}

  ngOnInit(): void {
    this.list();
  }

  list(){
    this.sgcOrdenService.getOrdenes().subscribe((res)=>{
      this.ordenes = res;
    })
  }

  cancelarOrden(id: number): void {
    this.sgcOrdenService.cancelarOrden(id).subscribe((res) => {
      console.log(res);
      // Actualizar la lista de órdenes después de cancelar
      this.list();
    });
  }


}
