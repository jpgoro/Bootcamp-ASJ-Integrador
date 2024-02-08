import { Component, OnInit } from '@angular/core';
import { SgcService } from '../../../services/sgc.service';
import { NgForm } from '@angular/forms';
import { Proveedor } from '../../../models/proveedor';
import { Router } from '@angular/router';
import { SupplierService } from '../../../services/supplier.service';
import { AddressService } from '../../../services/address.service';
import { IndustryService } from '../../../services/industry.service';
import { ContactService } from '../../../services/contact.service';
import { CountryService } from '../../../services/country.service';
import { ProvinceService } from '../../../services/province.service';
import { ConditionIvaService } from '../../../services/condition-iva.service';
import { Locality } from '../../../models/locality';
import { LocalityService } from '../../../services/locality.service';
import { Supplier } from '../../../models/supplier';
import { Industry } from '../../../models/industry';
import { ConditionIva } from '../../../models/condition-iva';
import { Contact } from '../../../models/contact';
import { Country } from '../../../models/country';
import { Province } from '../../../models/province';
import { Address } from '../../../models/address';

@Component({
  selector: 'app-form-proveedor',
  templateUrl: './form-proveedor.component.html',
  styleUrl: './form-proveedor.component.css',
})
export class FormProveedorComponent implements OnInit {
  constructor(public sgcService: SgcService, private router: Router,private countryService: CountryService,
    ) {}


    industryViewModel: Industry =
  { id: '',
  industryName: '', active: true };

  conditionIvaModel: ConditionIva = { id: '', taxCondition: '' };

  supplierViewModel: Supplier = {
    id: '',
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
    createdAt: '',
    updatedAt: '',
  };

  contactViewModel: Contact = {
    id: '',
    name: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    role: '',
    supplier: this.supplierViewModel,
    createdAt: '',
    updatedAt: '',
  };

  countryViewModel: Country = { id: '', name: '' };

  provinceViewModel: Province = {
    id: '',
    country: this.countryViewModel,
   name: '',
  };

  localityViewModel: Locality = {
    id: '',
    name: '',
    province: this.provinceViewModel,
  };
  addressViewModel: Address = {
    id: '',
    postalCode: '',
    street: '',
    number: 0,
    supplier: this.supplierViewModel,
    locality: this.localityViewModel,
    createdAt: '',
    updatedAt: '',
  };
  countries: Country[] = [];
  rubros: Industry[] = [];

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe(res=>{
        this.countries=res;
    })
  }


}



/* @Component({
  selector: 'app-form-proveedor',
  templateUrl: './form-proveedor.component.html',
  styleUrl: './form-proveedor.component.css',
})
export class FormProveedorComponent implements OnInit {



  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  constructor(
    private supplierService: SupplierService,
    private industryService: IndustryService,
    private contactService: ContactService,
    private addressService: AddressService,
    private countryService: CountryService,
    private provinceService: ProvinceService,
    private consitionIvaService: ConditionIvaService,
    private localityService: LocalityService,
  ){}

  industryViewModel: Industry =
  { id: '',
  industryName: '', active: true };

  conditionIvaModel: ConditionIva = { id: '', taxCondition: '' };

  supplierViewModel: Supplier = {
    id: '',
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
    createdAt: '',
    updatedAt: '',
  };

  contactViewModel: Contact = {
    id: '',
    name: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    role: '',
    supplier: this.supplierViewModel,
    createdAt: '',
    updatedAt: '',
  };

  countryViewModel: Country = { id: '', name: '' };

  provinceViewModel: Province = {
    id: '',
    country: this.countryViewModel,
   name: '',
  };

  localityViewModel: Locality = {
    id: '',
    name: '',
    province: this.provinceViewModel,
  };
  addressViewModel: Address = {
    id: '',
    postalCode: '',
    street: '',
    number: 0,
    supplier: this.supplierViewModel,
    locality: this.localityViewModel,
    createdAt: '',
    updatedAt: '',
  };
} */

