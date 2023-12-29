import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { NgForm } from '@angular/forms';
import { Proveedor } from '../../../models/proveedor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-proveedor',
  templateUrl: './form-proveedor.component.html',
  styleUrl: './form-proveedor.component.css',
})
export class FormProveedorComponent implements OnInit {
  constructor(public sgcService: SgcService, private router: Router) {}

  ngOnInit(): void {}

  createProveedor(form: NgForm) {

    if (form.value.id != -1) {
      this.sgcService.updateProveedor(form.value).subscribe((res) => {
        console.log(res);
        alert('Proveedor modificado correctamente');
        form.reset();
        this.router
          .navigateByUrl('/proveedor', { skipLocationChange: true })
          .then(() => {
            this.router.navigate(['/proveedor/tabla']);
          });
      });
    } else {
      this.sgcService.createProv(form.value).subscribe((res) => {
        console.log(res);
        alert('Proveedor creado correctamente');
        form.reset();
        this.sgcService.datosProveedor.id = -1;
        // Recargar la página después de la operación
        this.router
          .navigateByUrl('/proveedor', { skipLocationChange: true })
          .then(() => {
            this.router.navigate(['/proveedor/tabla']);
          });
      });
    }
  }
}
