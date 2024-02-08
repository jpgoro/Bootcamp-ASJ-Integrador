import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SgcProdService } from '../../../services/sgc-prod.service';
import { SgcService } from '../../../services/sgc.service';
import { SgcOcService } from '../../../services/sgc-oc.service';
import { Proveedor } from '../../../models/proveedor';
import { Producto } from '../../../models/producto';
import { Router } from '@angular/router';
import { PurchaseOrder } from '../../../models/purchase-order';
import { Supplier } from '../../../models/supplier';
import { Status } from '../../../models/status';


@Component({
  selector: 'app-form-oc',
  templateUrl: './form-oc.component.html',
  styleUrl: './form-oc.component.css'
})
export class FormOcComponent implements OnInit {
  proveedores: Proveedor[] = [];
  productos: Producto[] = [];
  productosFiltrados: Producto[] = [];
  constructor(public sgcProdService : SgcProdService, public sgcService : SgcService, public sgcOrdenService : SgcOcService, private router: Router){}
  auxSupplier!:Supplier;
  auxStatus!:Status;
  auxOrdenCompra: PurchaseOrder = {
    id: 0,
    number: '',
    issueDate: '',
    deliveryDate: '',
    active: true,
    reception: '',
    supplier: this.auxSupplier,
    status: this.auxStatus,
  }

  ngOnInit(): void {
    this.sgcService.getProveedores().subscribe((res)=>{
      this.proveedores=res;

    })

    this.sgcProdService.getPoductos().subscribe((res)=>{
      this.productos = res;
      this.productosFiltrados = res;
    })

  }

  onProveedorChange() {
    // Filtrar productos por proveedor
    const proveedorSeleccionado = this.sgcOrdenService.datosOrden.proveedor_razon;
    this.productosFiltrados = this.productos.filter(
      (producto) => producto.proveedor_razon === proveedorSeleccionado
    );
    // Resetear el producto seleccionado y el total al cambiar el proveedor
    this.sgcOrdenService.datosOrden.nombreProducto = '';
    this.sgcOrdenService.datosOrden.total = 0;
  }

  onCantidadChange() {
    
    const cantidad =parseFloat(this.sgcOrdenService.datosOrden.cantidad);
  const productoSeleccionado = this.productosFiltrados.find(
    (producto) => producto.nombreProducto === this.sgcOrdenService.datosOrden.nombreProducto
  );

  if (productoSeleccionado) {
    const precio = parseFloat(productoSeleccionado.precio);
    this.sgcOrdenService.datosOrden.total = cantidad * precio;
  }/*  else {
    // Si el producto seleccionado no está disponible, reiniciar el total
    this.sgcOrdenService.datosOrden.total = 0;
  } */


  }

  createOrden(form:NgForm){
    console.log(form.value)
    if(form.value.id !=-1){
      this.sgcOrdenService.updateOrden(form.value).subscribe((res)=>{
        console.log(res);
        alert('Orden modificada correctamente');
      form.reset();
      this.sgcOrdenService.datosOrden.id = -1;
      })
      console.log('modificando')
    }else{
    this.sgcOrdenService.createOrd(form.value).subscribe((res) =>{
      console.log(res);
      alert('Orden creada correctamente');
      form.reset();
      this.sgcOrdenService.datosOrden.id = -1;
      this.router
      .navigateByUrl('/oc', { skipLocationChange: true })
      .then(() => {
        this.router.navigate(['/oc/tabla']);
      });
    })
  }

  }

}
