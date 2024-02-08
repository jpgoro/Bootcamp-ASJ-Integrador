import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private readonly URL_API = 'http://localhost:8080/products';
  constructor(private http: HttpClient) {}

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.URL_API);
  }

  public getProductById(id: string): Observable<Product> {
    return this.http.get<Product>(`${this.URL_API}/${id}`);
  }

  public getProductsActive(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/active`);
  }

  public getProductsDeleted(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/deleted`);
  }

  public getProductsBySupplierId(supplierId: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/supplier/${supplierId}`);
  }

  public getProductsByPriceAsc(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/priceAsc`);
  }

  public getProductsByPriceDesc(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/priceDesc`);
  }

  public getProductsByCategory(categoryId: string): Observable<Product[]> {
    return this.http.get<Product[]>(
      `${this.URL_API}/category/${categoryId}`
    );
  }

  public postProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.URL_API,product);
  }

  public putProduct(id: string, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.URL_API}/${id}`,product);
  }

  public deleteProduct(id: string) :Observable<Product> {
    return this.http.delete<Product>(`${this.URL_API}/${id}`);
  }

  public patchProduct(id: string) :Observable<Product> {
    return this.http.patch<Product>(`${this.URL_API}/${id}/undelete`,true);
  }
}
