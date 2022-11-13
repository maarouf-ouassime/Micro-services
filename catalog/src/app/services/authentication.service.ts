import { Injectable } from '@angular/core';
import {AppUser} from "../model/user.model";
import {UUID} from "angular2-uuid";
import {Observable, of, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  users : AppUser[] = [];
  authenticatedUser: AppUser | undefined;
  constructor() {
    this.users.push({
      userId: UUID.UUID(),
      username: 'admin',
      password: 'admin',
      roles: ['ADMIN', 'USER']
    });
    this.users.push({
      userId: UUID.UUID(),
      username: 'user',
      password: 'user',
      roles: ['USER']
    });
  }

  public login(username: string, password: string) : Observable<AppUser>{
    let appUser = this.users.find(user => user.username === username);
    if (!appUser) {
      return throwError(() => new Error('User not found'));
    }
    if (appUser.password !== password) {
      return throwError(() => new Error('Invalid password'));
    }
    return of(appUser);
  }

  public authenticateUser(appUser: AppUser):Observable<boolean> {
    this.authenticatedUser = appUser;
    localStorage.setItem('authenticatedUser',
      JSON.stringify({
        userId: appUser.userId,
        username: appUser.username,
        roles: appUser.roles,
        jwtToken: 'JWT_TOKEN'
      }));
    return of(true);
  }

  public hasRole(role: string): boolean {
    return this.authenticatedUser!.roles.includes(role);
  }
  public isAuthenticated(): boolean {
    return this.authenticatedUser!= undefined;
  }

  public logout() :Observable<boolean>{
    this.authenticatedUser = undefined;
    localStorage.removeItem('authenticatedUser');
    return of(true);
  }
}
