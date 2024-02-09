import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SgcService } from '../../../services/sgc.service';
import { SgcProdService } from '../../../services/sgc-prod.service';

@Component({
  selector: 'app-detalles',
  templateUrl: './detalles.component.html',
  styleUrl: './detalles.component.css'
})
export class DetallesComponent implements OnInit  {
  constructor(public sgcProdService : SgcProdService, public sgcService : SgcService, private router : Router, private route: ActivatedRoute){}
  productViewModel: any = {
    id: 0,
    sku: '',
    name: '',
    description: '',
    image: '',
    price: 0,
    active: true,
    supplier: "",
    category: "",
  };
  ngOnInit(): void {
    this.route.paramMap.subscribe((response) => {
      let id = response.get('id');
      if(id!==null){
        this.sgcProdService.getProductById(Number(id)).subscribe((res)=>{
          this.productViewModel.id = res.id;
          this.productViewModel.sku = res.sku;
          this.productViewModel.name = res.name;
          this.productViewModel.description = res.description;
          this.productViewModel.image = res.image;
          this.productViewModel.price = res.price;
          this.productViewModel.active = res.active;
          this.productViewModel.supplier = res.supplier.legalName;
          this.productViewModel.category = res.category.name;
        });
      }
    })

  }

}
