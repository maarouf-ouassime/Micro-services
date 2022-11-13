import { Component, OnInit } from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products! : Array<Product> ;
  errorMessage!: string;
  searchFormGroup!: FormGroup;
  currentPage :number= 0;
  pageSize: number = 5;
  totalPages: number=0;
  currentAction: string="all";
  constructor(private router: Router,
              public authService: AuthenticationService,
              private fb : FormBuilder,
              private productService: ProductService) { }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control(null)
    });
   // this.handleGetProducts();
    this.handleGetPageProducts();
  }

  handleGetProducts() {
    this.productService.getProducts().subscribe({
      next: data => {
        this.products = data;
      },
      error: err => {
        this.errorMessage = err;
      }
    });
  }

  handleGetPageProducts() {
    this.productService.getPageProducts(this.currentPage,this.pageSize).subscribe({
      next: data => {
        this.products = data.products;
        this.totalPages = data.totalPages;
      },
      error: err => {
        this.errorMessage = err;
      }
    });
  }

  handleDeleteProduct(product: Product) {
    let conf = confirm('Are you sure?');
    if (conf === false) return;
    this.productService.deleteProduct(product.id).subscribe({
      next: data => {
        //this.handleGetProducts();
        let index = this.products.indexOf(product);
        this.products.splice(index, 1);
      }
    });
    /*let index = this.products.indexOf(product);
    this.products.splice(index, 1);*/
  }

  handelSetPromotion(product: Product) {
    let promo = product.promotion;
    this.productService.setPromotion(product.id).subscribe({
      next: data => {
        product.promotion = !promo;
      },
      error: err => {
        this.errorMessage = err.message;
      }
    });
  }

  handelSearchProducts() {
    this.currentAction = "search";
    //this.currentPage = 0;
    let keyword = this.searchFormGroup.value.keyword;
     this.productService.searchProducts(keyword,this.currentPage,this.pageSize).subscribe({
        next: data => {
          this.products = data.products;
          this.totalPages = data.totalPages;
        }
     });
     if (keyword === null || keyword === '') {
       this.currentAction = "all";
       this.currentPage = 0;
       this.handleGetPageProducts();
     }

  }

  goToPage(i: number) {
    this.currentPage = i;
    if (this.currentAction === "all") {
      this.handleGetPageProducts();
    }else {
      this.handelSearchProducts();
    }
  }

  handleNewProduct() {
    this.router.navigateByUrl('/admin/newProduct');
  }

  handleEditProduct(product: Product) {
    this.router.navigateByUrl('/admin/editProduct/'+product.id);
  }
}
