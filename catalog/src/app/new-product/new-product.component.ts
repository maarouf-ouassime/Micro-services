import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {ProductService} from "../services/product.service";

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  productFormGroup!: FormGroup;
  constructor(public productService:ProductService,
              private fb:FormBuilder) { }

  ngOnInit(): void {
    this.productFormGroup = this.fb.group({
     name: this.fb.control(null,
       [
          Validators.required,
          Validators.minLength(3),
       ]),
      price: this.fb.control(null,[
        Validators.required,
        Validators.min(0)
      ]),
      description: this.fb.control(null),
      promotion: this.fb.control(false,[Validators.required])
    });
  }

  handleAddProduct() {
    let product = this.productFormGroup.value;
    this.productService.addNewProduct(product).subscribe({
      next: (data) => {
        alert('Product added successfully');
        this.productFormGroup.reset();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }


}
