import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SgcProdService } from '../../../services/sgc-prod.service';
import { SgcService } from '../../../services/sgc.service';
import { SgcOcService } from '../../../services/sgc-oc.service';
import { Proveedor } from '../../../models/proveedor';
import { Producto } from '../../../models/producto';
import { ActivatedRoute, Router } from '@angular/router';
import { PurchaseOrder } from '../../../models/purchase-order';
import { Supplier } from '../../../models/supplier';
import { Status } from '../../../models/status';
import { DetailOrder } from '../../../models/detail-order';
import { Product } from '../../../models/product';


@Component({
  selector: 'app-form-oc',
  templateUrl: './form-oc.component.html',
  styleUrl: './form-oc.component.css'
})
export class FormOcComponent implements OnInit {
  proveedores: Supplier[] = [];
  estados: Status[] = [];
  productos: Product[] = [];
  productosFiltrados: Product[] = [];
  detalles : DetailOrder[] = [];
  constructor(public sgcProdService : SgcProdService, public sgcService : SgcService, public sgcOrdenService : SgcOcService, private router: Router, private route: ActivatedRoute){}
  auxSupplier!:Supplier;
  auxStatus!:Status;
  auxProducto!:Producto;
  auxPurchase!:PurchaseOrder;
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
  auxDetailOrder : DetailOrder={
    id: 0,
    quantity: 0,
    price: 0,
    product: {
      id: 0,
      sku: '',
      name: '',
      description: '',
      image: '',
      price: 0,
      active: true,
      supplier: {
        id: 0,
    code: '',
    legalName: '',
    active: true,
    cuit: '',
    email: '',
    image: '',
    tel: '',
    web: '',
    industry:{
      id: 0,
  industryName: '', active: true
    },
    conditionIva: {
      id: 0, taxCondition: ''
    },
    contact:[],
      },
      category: {id: 0,
        name: "",
        active: true},
    },
    purchaseOrder: this.auxPurchase,
  }

  ngOnInit(): void {
    this.sgcService.getAllSuppliers().subscribe((res)=>{
      this.proveedores = res;
    })

    this.sgcProdService.getProducts().subscribe((res)=>{
      this.productos = res;
    })

    this.sgcOrdenService.getStatus().subscribe((res)=>{
      this.estados = res;
    })


  }

  /* onProveedorChange(id: number): void {
    console.log(id);
    // Filtrar productos por proveedor
    this.sgcProdService.getProductsBySupplierId(id).subscribe((res)=>{
      this.productosFiltrados = res;
    });
  } */

  onProveedorChange(id: number): void {
    console.log(id);
    // Filtrar productos por proveedor
    this.sgcProdService.getProductsBySupplierId(id).subscribe((res)=>{
      this.productosFiltrados = res;
    });
  }

  cargarArray(idProd:number){
    console.log(idProd);
    const prodActual = this.productosFiltrados.find((product)=>Number(product.id) === Number(idProd));
    console.log(prodActual);
    if(prodActual){
      const actualDetail = this.detalles.findIndex((detalle : DetailOrder)=>{
        return Number(detalle.product.id) === Number(idProd);
      });
      if(actualDetail !== -1){
        this.detalles[actualDetail].quantity+=this.auxDetailOrder.quantity;
      }else{
        const newDetail = {
          product: prodActual,
          price: prodActual.price,
          quantity:this.auxDetailOrder.quantity
        }
        this.detalles.push(newDetail);

      }
      console.log(this.detalles)
  }
  }

  createOrden(form: NgForm){
    const newOrder : PurchaseOrder = {
    number: form.value.numeroOrden,
    issueDate:form.value.fechaEmision + 'T00:00:00' ,
    deliveryDate: form.value.fechaEntrega + 'T00:00:00',
    active: true,
    reception: form.value.reception,
   status: {
    id: form.value.status,
  },
  supplier: {
    id: form.value.proveedor_razon
  }
    }
    console.log(newOrder);
    this.sgcOrdenService.postOrder(newOrder).subscribe(res => {
      console.log(res);
      this.detalles.forEach(element => {
        element.purchaseOrder = {
          id: res.id
        }
      });
      this.sgcOrdenService.postDetailOrder(this.detalles).subscribe(res => {
        console.log(res);
      })
    })

  }


}


 /*  onCantidadChange() {

    const cantidad =parseFloat(this.sgcOrdenService.datosOrden.cantidad);
  const productoSeleccionado = this.productosFiltrados.find(
    (producto) => producto.nombreProducto === this.sgcOrdenService.datosOrden.nombreProducto
  );

  if (productoSeleccionado) {
    const precio = parseFloat(productoSeleccionado.precio);
    this.sgcOrdenService.datosOrden.total = cantidad * precio;  */
  /*  else {
    // Si el producto seleccionado no está disponible, reiniciar el total
    this.sgcOrdenService.datosOrden.total = 0;
  }




  /* createOrden(form:NgForm){
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

  } */


