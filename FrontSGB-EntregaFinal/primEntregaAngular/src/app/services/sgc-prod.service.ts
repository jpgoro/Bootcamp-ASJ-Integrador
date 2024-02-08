import { Injectable } from '@angular/core';
import { Producto } from '../models/producto';
import { Proveedor}from '../models/proveedor'
import { Observable, from, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { Category } from '../models/category';



@Injectable({
  providedIn: 'root'
})
export class SgcProdService {

  private lastId2: number = 0;
  productos: Producto[] = [];

  datosProducto: Producto = {
    id:-1,
    proveedor_id:0,//proveedor seleccionado de una lista de Tipo Proveedor
    proveedor_razon:'',
    sku: '',
    categoria: '',
    nombreProducto: '',
    descripcion: '',
    precio: ''
  }

  private readonly URL_API = 'http://localhost:8080/products';
  private readonly URL_API2 = 'http://localhost:8080/categories';
  constructor(private http: HttpClient) {
    this.loadProductosFromLocalStorage();
  }


  //products
  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.URL_API);
  }

  public getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.URL_API}/${id}`);
  }

  public getProductsActive(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/active`);
  }

  public getProductsDeleted(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/deleted`);
  }

  public getProductsBySupplierId(supplierId: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/supplier/${supplierId}`);
  }

  public getProductsByPriceAsc(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/priceAsc`);
  }

  public getProductsByPriceDesc(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_API}/priceDesc`);
  }

  public getProductsByCategory(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>(
      `${this.URL_API}/category/${categoryId}`
    );
  }

  public postProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.URL_API,product);
  }

  public putProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.URL_API}/${id}`,product);
  }

  public deleteProduct(id: number) :Observable<Product> {
    return this.http.delete<Product>(`${this.URL_API}/${id}`);
  }

  public patchProduct(id: number) :Observable<Product> {
    return this.http.patch<Product>(`${this.URL_API}/${id}/undelete`,true);
  }


  

// Categorias
public getCategory(): Observable<Category[]> {
  return this.http.get<Category[]>(this.URL_API2);
}

public getCategoryById(id:number): Observable<Category> {
  return this.http.get<Category>(this.URL_API2 + '/' + id);
}

public getActiveCategories(): Observable<Category[]> {
  return this.http.get<Category[]>(this.URL_API2 + '/active');
}

public getDeletedCategories(): Observable<Category[]> {
  return this.http.get<Category[]>(this.URL_API2 + '/deleted');
}

public postCategory(category:Category): Observable<Category> {
  return this.http.post<Category>(this.URL_API2, category);
}

public putCategory(category:Category): Observable<Category> {
  return this.http.put<Category>(this.URL_API2 + '/' + category.id, category);
}

public deleteCategory(id:number): Observable<Category> {
  return this.http.delete<Category>(this.URL_API2 + '/' + id);
}

public undeleteCategoryById(id:number): Observable<Category> {
  return this.http.patch<Category>(this.URL_API2 + '/undelete/' + id, {});
}






















  //viejossssssssssssss
  private loadProductosFromLocalStorage() {
    const storedProductos = localStorage.getItem('productos');
    if (storedProductos) {
      this.productos = JSON.parse(storedProductos);
      //busco ultimo id almacenado
      const lastProducts = this.productos[this.productos.length - 1];
      this.lastId2 = lastProducts ? lastProducts.id : 0;
    }
  }
  public createProd(producto: Producto): Observable<any> {
    this.lastId2++;
    producto.id = this.lastId2;
      this.productos.push(producto);
      localStorage.setItem('productos', JSON.stringify(this.productos));
      return of({ message: 'Producto creado exitosamente' });

    }
    public getPoductos(): Observable<Producto[]> {
      this.loadProductosFromLocalStorage();
      return of(this.productos);
    }
    public updateProducto(producto: Producto): Observable<any> {
      const index = this.productos.findIndex((p) => p.id === producto.id);
      if (index !== -1) {
        this.productos[index] = producto;
        localStorage.setItem('productos', JSON.stringify(this.productos));
        return of({ message: 'Producto actualizado exitosamente' });
      } else {
        return of({ error: 'Producto no encontrado' });
      }
    }
    public deleteProducto(id: number): Observable<any> {
      // Filtrar el proveedor a eliminar
      const productoAEliminar = this.productos.find((p) => p.id === id);

      if (productoAEliminar) {
        // Filtrar la lista para quitar el proveedor
        this.productos = this.productos.filter((p) => p.id !== id);

        // Guardar la lista actualizada en el almacenamiento local
        localStorage.setItem('productos', JSON.stringify(this.productos));

        // Puedes retornar algún mensaje o información útil
        return of({ message: 'Porducto eliminado exitosamente' });
      } else {
        // Si el proveedor no se encuentra, puedes retornar un mensaje de error o algo similar
        return of({ error: 'Producto no encontrado' });
      }
    }
}
