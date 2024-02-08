import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { NgForm } from '@angular/forms';
import { Proveedor } from '../../../models/proveedor';
import { Router } from '@angular/router';
import { Industry } from '../../../models/industry';
import { ConditionIva } from '../../../models/condition-iva';
import { Supplier } from '../../../models/supplier';
import { Contact } from '../../../models/contact';
import { Country } from '../../../models/country';
import { Province } from '../../../models/province';
import { Locality } from '../../../models/locality';
import { Address } from '../../../models/address';

@Component({
  selector: 'app-form-proveedor',
  templateUrl: './form-proveedor.component.html',
  styleUrl: './form-proveedor.component.css',
})
export class FormProveedorComponent implements OnInit {
  constructor(public sgcService: SgcService, private router: Router) {}
  industryViewModel: Industry =
  { id: 0,
  industryName: '', active: true };

  conditionIvaModel: ConditionIva = { id: 0, taxCondition: '' };

  supplierViewModel: Supplier = {
    id: 0,
    code: '',
    legalName: '',
    active: true,
    cuit: '',
    email: '',
    image: '',
    tel: '',
    web: '',
    industry: this.industryViewModel,
    conditionIva: this.conditionIvaModel,
    contact:[],
  };

  contactViewModel: Contact = {
    id: 0,
    name: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    role: '',
    supplier: this.supplierViewModel,
  };

  countryViewModel: Country = { id: 0, name: '' };

  provinceViewModel: Province = {
    id: 0,
    country: this.countryViewModel,
   name: '',
  };

  localityViewModel: Locality = {
    id: 0,
    localityName: '',
    province: this.provinceViewModel,
  };

  addressViewModel: Address = {
    id: 0,
    postalCode: '',
    street: '',
    number: 0,
    supplier: this.supplierViewModel,
    locality: this.localityViewModel,
  };
  countries: Country[] = [];
  rubros: Industry[] = [];
  iva: ConditionIva[] = [];
  provinces: Province[] = [];
  editMode = false;
  ngOnInit(): void {
    this.sgcService.getAllCountries().subscribe(res=>{
      this.countries=res;
  })

  this.sgcService.getIvaConditions().subscribe(res=>{
    this.iva=res;
  })
  this.sgcService.getAllIndustries().subscribe(res=>{
    this.rubros=res;
  })
  }

  selectCountry(){
    console.log(this.addressViewModel.locality.province.country.id)
    this.sgcService.getProvincesByCountryId(this.addressViewModel.locality.province.country.id).subscribe(res=>{
      this.provinces = res;
    })
  }
  createSupplier(form:NgForm){
    console.log(this.supplierViewModel)
      this.sgcService.postSupplier(this.supplierViewModel).subscribe(res=>{
        console.log(res);
        this.addressViewModel.supplier.id = res.id
        this.contactViewModel.supplier.id = res.id
        console.log(this.addressViewModel);
        this.sgcService.postLocality(this.addressViewModel.locality).subscribe(res=>{
          this.addressViewModel.locality.id = res.id;

          this.sgcService.postAddress(this.addressViewModel).subscribe(res=>{
            console.log(res);

          });
          console.log(res);
        });

        this.sgcService.postContact(this.contactViewModel).subscribe(res=>{
          console.log(res);
        });


      });

  }




//Codigo viejoooooooooooooooo
  createProveedor(form: NgForm) {



    if (form.value.id != -1) {
      this.sgcService.updateProveedor(form.value).subscribe((res) => {
        console.log(res);
        alert('Proveedor modificado correctamente');
        form.reset();
        this.sgcService.datosProveedor.id = -1;
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
