import { Injectable } from '@angular/core';
import {Observable, of, throwError} from "rxjs";
import {PageProduct, Product} from "../model/product.model";
import {UUID} from "angular2-uuid";
import {ValidationErrors} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private products!: Array<Product>;

  constructor() {
    this.products = [
      {
        id: UUID.UUID(),
        name: 'Laptop',
        price: 2000,
        description: 'New Mac pro',
        promotion: false
      },
      {
        id: UUID.UUID(),
        name: 'Mobile',
        price: 800,
        description: 'New Iphone 12',
        promotion: true
      },
      {
        id: UUID.UUID(),
        name: 'TV',
        price: 1000,
        description: 'New Smart TV',
        promotion: true
      }
    ];
    for (let i = 0; i < 15; i++) {
      this.products.push({
        id: UUID.UUID(),
        name: 'Product ' + i,
        price: Math.floor(Math.random() * 1000),
        description: 'New Product ' + i,
        promotion: Math.random() < 0.5
      });
      }
    }



  public getProducts(): Observable<Array<Product>> {
    let rnd = Math.random();
    if (rnd < 0.01) {
      return throwError('Error: Something went wrong');
    } else {
      return of([...this.products]);
    }
  }

  public getPageProducts(page: number,size: number): Observable<PageProduct> {
    let index = page * size;
    let totalPages = ~~(this.products.length/size);
     if (this.products.length % size != 0) {
       totalPages++;
     }
   let pageProducts = this.products.slice(index, index + size);
    return of({page: page, size: size, totalPages: totalPages, products: pageProducts});
  }

  public deleteProduct(id: string): Observable<boolean> {
    this.products = this.products.filter(product => product.id !== id);
    return of(true);
  }

  public setPromotion(id: string): Observable<boolean> {
    let product = this.products.find(product => product.id === id);
    if (product) {
      product.promotion = !product.promotion;
      return of(true);
    }else {
      return  throwError(new Error('Product not found'));
    }
  }

  /*public searchProducts(keyword: string): Observable<Array<Product>> {
    let result = this.products.filter(product => product.name.toLowerCase().includes(keyword.toLowerCase()));
    return of(result);
  }*/

  public searchProducts(keyword: string,page: number, size: number): Observable<PageProduct> {
    let result = this.products.filter(product => product.name.toLowerCase().includes(keyword.toLowerCase()));
    let index = page * size;
    let totalPages = ~~(result.length/size);
    if (result.length % size != 0) {
      totalPages++;
    }
    let pageProducts = result.slice(index, index + size);
    return of({page: page, size: size, totalPages: totalPages, products: pageProducts});

  }

  public addNewProduct(product: Product): Observable<Product> {
    product.id = UUID.UUID();
    this.products.push(product);
    return of(product);
  }

  public getProduct(id: string): Observable<Product> {
    let product = this.products.find(product => product.id === id);
    if (product) {
      return of(product);
    }else {
      return throwError(()=> new Error('Product not found'));
    }
  }
  getErrorMessage(fieldName: string, error: ValidationErrors) {
    if(error['required']) {
      return fieldName + ' is required';
    }else if(error['minlength']) {
      return fieldName + ' must be at least '+error['minlength']['requiredLength']+' characters';
    }else if(error['min']) {
      return fieldName + ' should have min value ' + error['min']['min'];
    }else return "";
  }

  public updateProduct(product: Product): Observable<Product> {
   this.products = this.products.map(p => p.id === product.id ? product : p);
    return of(product);
  }
}
