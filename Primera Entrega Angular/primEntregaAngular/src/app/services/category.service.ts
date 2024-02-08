import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  constructor(public http:HttpClient) { }
  private readonly URL_API = 'http://localhost:8080/categories';

  public getCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(this.URL_API);
  }

  public getCategoryById(id:string): Observable<Category> {
    return this.http.get<Category>(this.URL_API + '/' + id);
  }

  public getActiveCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.URL_API + '/active');
  }

  public getDeletedCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.URL_API + '/deleted');
  }

  public postCategory(category:Category): Observable<Category> {
    return this.http.post<Category>(this.URL_API, category);
  }

  public putCategory(category:Category): Observable<Category> {
    return this.http.put<Category>(this.URL_API + '/' + category.id, category);
  }

  public deleteCategory(id:string): Observable<Category> {
    return this.http.delete<Category>(this.URL_API + '/' + id);
  }

  public undeleteCategoryById(id:string): Observable<Category> {
    return this.http.patch<Category>(this.URL_API + '/undelete/' + id, {});
  }

}
