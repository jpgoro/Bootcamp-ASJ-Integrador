import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { main } from '@popperjs/core';
import { MainComponent } from './components/main/main.component';
import { FormProveedorComponent } from './components/proveedor/form-proveedor/form-proveedor.component';
import { FormProductoComponent } from './components/producto/form-producto/form-producto.component';
import { TarjProductoComponent } from './components/producto/tarj-producto/tarj-producto.component';
import { TarjProveedorComponent } from './components/proveedor/tarj-proveedor/tarj-proveedor.component';
import { FormOcComponent } from './components/oc/form-oc/form-oc.component';
import { TarjOcComponent } from './components/oc/tarj-oc/tarj-oc.component';

const routes: Routes = [
  {path:'', component:MainComponent},
  {path:'proveedor', component:FormProveedorComponent},
  {path: 'proveedor/tarjeta', component:TarjProveedorComponent},
  {path: 'producto', component:FormProductoComponent },
  {path: 'producto/tarjeta', component:TarjProductoComponent},
  {path: 'oc', component:FormOcComponent},
  {path: 'oc/tarjeta', component:TarjOcComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
