import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { NgForm } from '@angular/forms';
import { Proveedor } from '../../../models/proveedor';
import { ActivatedRoute, Router } from '@angular/router';
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
  constructor(public sgcService: SgcService, private router: Router,private route: ActivatedRoute,) {}
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
  this.route.paramMap.subscribe((response) => {
    let id = response.get('id');
if(id!==null ){
  this.editMode = true;
  this.sgcService.getSupplierById(Number(id)).subscribe(res=>{
    this.supplierViewModel.id = res.id;
    this.supplierViewModel.code = res.code;
    this.supplierViewModel.legalName = res.legalName;
    this.supplierViewModel.active = res.active;
    this.supplierViewModel.cuit = res.cuit;
    this.supplierViewModel.email = res.email;
    this.supplierViewModel.image = res.image;
    this.supplierViewModel.tel = res.tel;
    this.supplierViewModel.web= res.web;
    this.industryViewModel.id = res.industry.id;
    this.industryViewModel.industryName = res.industry.industryName;
    this.conditionIvaModel.id = res.conditionIva.id;
    this.conditionIvaModel.taxCondition = res.conditionIva.taxCondition;
    console.log(res.industry)
  })
  this.sgcService.getAddressBySupplierId(Number(id)).subscribe(res =>{
    console.log(res);
    this.addressViewModel.id = res[0].id;
    this.addressViewModel.postalCode = res[0].postalCode;
    this.addressViewModel.street = res[0].street;
    this.addressViewModel.number = res[0].number;
    this.localityViewModel.id = res[0].locality.id;
    this.localityViewModel.localityName= res[0].locality.localityName;
   this.provinceViewModel.id = res[0].locality.province.id;
   this.provinceViewModel.name = res[0].locality.province.name;
   this.countryViewModel.id = res[0].locality.province.country.id;
   this.countryViewModel.name = res[0].locality.province.country.name;
   this.selectCountry();
  })
  this.sgcService.getContactBySupplier(Number(id)).subscribe(res =>{
    this.contactViewModel.id = res[0].id;
    this.contactViewModel.name = res[0].name;
    this.contactViewModel.lastName = res[0].lastName;
    this.contactViewModel.email = res[0].email;
    this.contactViewModel.phoneNumber = res[0].phoneNumber;
    this.contactViewModel.role = res[0].role;

  })


}
  });
  }

  selectCountry(){
    console.log(this.addressViewModel.locality.province.country.id)
    this.sgcService.getProvincesByCountryId(this.addressViewModel.locality.province.country.id).subscribe(res=>{
      this.provinces = res;
    })
  }


  createSupplier(form:NgForm){
    console.log(this.supplierViewModel)
    if(this.editMode){
      this.sgcService.putSupplier(this.supplierViewModel.id, this.supplierViewModel).subscribe(res =>{
        this.sgcService.putAddress(this.addressViewModel.id, this.addressViewModel).subscribe();
        this.sgcService.putContact(this.contactViewModel.id, this.contactViewModel).subscribe();
      },err=>{console.log(err);});
    }else{
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
