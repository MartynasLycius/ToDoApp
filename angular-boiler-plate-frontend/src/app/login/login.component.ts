import { Component, OnInit } from "@angular/core";
import { LoginService } from "./login.service";
import { FormGroup, FormControl } from "@angular/forms";
import { AuthService } from "./auth.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  public form;

  constructor(
    public loginService: LoginService,
    public authService: AuthService,
    public router: Router
  ) {
    this.form = new FormGroup({
      username: new FormControl(""),
      password: new FormControl(""),
    });
  }

  ngOnInit(): void {
    if (this.authService.getBasicToken()) {
      this.checkLogin();
    }
  }

  onSubmit(data) {
    this.loginService.checkLogin(
      data,
      (response) => {
        this.authService.setBasicToken(response.body.token);
        this.loginService.oauthLogin(
          this.form.value,
          (responseOauth) => {
            this.authService.setToken(responseOauth.body);
            this.router.navigate(["/dashboard"]);
          },
          (errorOauth) => {
            console.log(errorOauth);
          }
        );
      },
      (error) => {
        console.log(error);
      }
    );
  }

  checkLogin() {
    this.loginService.userInfo(
      (response) => {
        this.router.navigate(["/dashboard"]);
      },
      () => {}
    );
  }
}
