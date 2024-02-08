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
  { path: '', component: MainComponent },
  {
    path: 'proveedor',
    children: [{ path: 'formAdd', component: FormProveedorComponent }
    ,{ path: 'formAdd/:id', component: FormProveedorComponent },
    { path: 'TablaForm', component: TarjProveedorComponent }],
  },

  {
    path: 'producto',
    children: [{ path: 'formAdd', component: FormProductoComponent },{ path: 'TablaForm', component: TarjProductoComponent },],
  },

  { path: 'oc', children: [{ path: 'tabla', component: FormOcComponent },  { path: 'oc', component: TarjOcComponent },] },
  {path: '**', pathMatch: 'full', redirectTo: ''},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
