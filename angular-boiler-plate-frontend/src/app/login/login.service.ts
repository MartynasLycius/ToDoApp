import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "./../../environments/environment";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: "root"
})
export class LoginService {
  public accessMenu = [];
  constructor(private http: HttpClient, public auth: AuthService) {}

  checkLogin(data, successCallback, errorCallback) {
    let formData: FormData = new FormData();
    formData.append("username", data.username);
    formData.append("password", data.password);
    this.http
      .post(environment.baseUrl + "/public/login/check", formData, {
        observe: "response"
      })
      .subscribe({
        next: response => successCallback(response),
        error: error => errorCallback(error)
      });
  }

  oauthLogin(data, successCallback, errorCallback) {
    data.grant_type = "password";
    this.http
      .post(environment.baseUrl + "/oauth/token", data, {
        observe: "response"
      })
      .subscribe({
        next: response => successCallback(response),
        error: error => errorCallback(error)
      });
  }

  userInfo(successCallback, errorCallback) {
    this.http
      .get(environment.baseUrl + "/api/users/info", { observe: "response" })
      .subscribe({
        next: response => {
          //@ts-ignore
          this.accessMenu = response.body.accessMenu;
          successCallback(response);
        },
        error: error => {
          errorCallback(error);
        }
      });
  }

  logoutUser() {
    this.auth.setBasicToken(null);
    this.auth.setToken(null);
  }
}
