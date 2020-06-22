import { Injectable } from "@angular/core";
import "rxjs/add/operator/map";
import { environment } from "./../../environments/environment";
import {
  HttpClient,
  HttpHeaders,
  HttpBackend,
  HttpRequest,
  HttpEvent,
  HttpResponse,
  HttpHandler
} from "@angular/common/http";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthService } from "./auth.service";
import { Observable } from "rxjs";

@Injectable()
export class RefreshTokenService {
  public env = environment;
  headers: any;
  public csrf: any;
  private http: HttpClient;

  constructor(
    public router: Router,
    public auth: AuthService,
    httpBackend: HttpBackend
  ) {
    this.http = new HttpClient(httpBackend);
  }

  async refreshToken(basicAuthToken, request, next) {
    let data = await this.http
      .post(this.env.baseUrl + "/oauth/token", this.getRefreshTokenPayload(), {
        headers: this.getBasicAuthHeader(basicAuthToken),
        observe: "response",
        withCredentials: true
      })
      .toPromise();
    if (data["status"] == 200) {
      this.auth.setToken(data["body"]);
      request = this.setAuthorizationHeader(request);
      let newResponse: HttpEvent<any> = await this.http
        .request(request.method, request.url, {
          body: request.body,
          headers: request.headers,
          observe: "response",
          withCredentials: true
        })
        .toPromise();

      // return next.handle(request)
      //       .do(event => {
      //   return event;
      // })
      return newResponse;
    }
    return Observable.throw(data);
  }

  getRefreshTokenPayload() {
    let payload = {
      grant_type: "refresh_token",
      refresh_token: this.auth.getToken().refresh_token
    };
    return payload;
  }

  getBasicAuthHeader(basicAuthToken) {
    let headers = new HttpHeaders().set("Content-Type", "application/json");
    headers = headers.set("Authorization", `Basic ${basicAuthToken}`);
    return headers;
  }

  setAuthorizationHeader(request) {
    request = request.clone({
      headers: request.headers.set(
        "Authorization",
        `Bearer ${this.auth.getToken().access_token}`
      )
    });
    return request;
  }
}
