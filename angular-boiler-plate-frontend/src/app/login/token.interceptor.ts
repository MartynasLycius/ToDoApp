import { Injectable } from "@angular/core";
import {
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse
} from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { map, catchError } from "rxjs/operators";
import { AuthService } from "./auth.service";
import { environment } from "./../../environments/environment";
import { RefreshTokenService } from "./refresh.token.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  public env = environment;

  constructor(
    public auth: AuthService,
    public refreshTokenService: RefreshTokenService
  ) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (request.url.indexOf("/oauth/token") !== -1) {
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ${this.auth.getBasicToken()}`
        }
      });
    } else if (
      request.url.indexOf("/public/resetPassword") !== -1 ||
      request.url.indexOf("/login/check") !== -1
    ) {
    } else {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.auth.getToken().access_token}`
        }
      });
      // if (request.url.indexOf("/selectTenant") == -1) {
      //   request = request.clone({
      //     setHeaders: {
      //       Authorization: `Bearer ${this.auth.getToken().access_token}`,
      //       "X-TENANT-ACCESSOR-TOKEN": `${this.auth.getTenantToken()}`
      //     }
      //   });
      // }
    }
    //@ts-ignore
    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
        return event;
      }),
      catchError((err: HttpErrorResponse) => {
        if (err.status == 401) {
          return this.refreshTokenService.refreshToken(
            this.auth.getBasicToken(),
            request,
            next
          );
        }
        return Observable.throw(err);
      })
    );
    // next
    //   .handle(request)
    //   .do(event => {
    //     return event;
    //   })
    //   .catch(err => {
    //     if (err.status == 401) {
    //       return this.refreshTokenService.refreshToken(
    //         this.auth.getBasicToken(),
    //         request,
    //         next
    //       );
    //     }
    //     return Observable.throw(err);
    //   });
  }
}
