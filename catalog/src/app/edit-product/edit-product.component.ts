import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  productId!: string;
  product!: Product;
  productFormGroup!: FormGroup;
  constructor(public productService: ProductService,private fb: FormBuilder,
              private route: ActivatedRoute) {
    this.productId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.productService.getProduct(this.productId).subscribe({
      next: product => {
        this.product = product;
        this.productFormGroup = this.fb.group({
          name: this.fb.control(this.product.name,
            [
              Validators.required,
              Validators.minLength(3),
            ]),
          price: this.fb.control(this.product.price,[
            Validators.required,
            Validators.min(0)
          ]),
          description: this.fb.control(this.product.description),
          promotion: this.fb.control(this.product.promotion,[Validators.required])
        });
      },
      error: err => console.log(err)
    });
  }


  handleUpdateProduct() {
   let product = this.productFormGroup.value;
   product.id = this.product.id;
    this.productService.updateProduct(product).subscribe({
      next: (data) => {
        alert('Product updated successfully');
        this.productFormGroup.reset();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}
