import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthenticationService} from "../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessages! :any;
  userFormGroup! : FormGroup;
  constructor(private router: Router,private authService: AuthenticationService,private fb: FormBuilder) { }

  ngOnInit(): void {
    this.userFormGroup = this.fb.group({
      username: this.fb.control(''),
      password: this.fb.control('')
    })
  }

  handleLogin() {
    let username = this.userFormGroup.value.username;
    let password = this.userFormGroup.value.password;
    this.authService.login(username, password).subscribe(
      appUser => {
        this.authService.authenticateUser(appUser).subscribe({
          next: (data) => {
            this.router.navigateByUrl('/admin');
          }
        });
      },
      error => {
        this.errorMessages = error;
      });

  }
}
