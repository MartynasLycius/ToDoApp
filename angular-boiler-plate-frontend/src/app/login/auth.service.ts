import { Injectable } from "@angular/core";

@Injectable()
export class AuthService {
  public getToken() {
    if(localStorage.getItem("tokenObject")==null) {
      return {};
    }
    return JSON.parse(localStorage.getItem("tokenObject"));
  }
  public setToken(data) {
    localStorage.setItem('tokenObject', JSON.stringify(data));
  }
  public setBasicToken(token) {
    localStorage.setItem('clientCredentials', token);
  }
  public getBasicToken() {
    return localStorage.getItem("clientCredentials")
  }
  public setTenantToken(token) {
    localStorage.setItem('tenantToken', token);
  }
  public getTenantToken() {
    return localStorage.getItem("tenantToken")
  }
  public isAuthenticated(): boolean {
    const token = this.getToken();
    return true;
  }
}
