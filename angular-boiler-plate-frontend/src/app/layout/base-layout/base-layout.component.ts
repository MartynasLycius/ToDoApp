import { Component, OnInit } from "@angular/core";
import { Output, EventEmitter } from "@angular/core";
import { LoginService } from "./../../login/login.service";
import { ActivatedRoute, Router } from "@angular/router";
import { PageAccessCheckService } from "src/app/dashboard/page-access-check.service";

@Component({
  selector: "app-base-layout",
  templateUrl: "./base-layout.component.html",
  styleUrls: ["./base-layout.component.css"],
})
export class BaseLayoutComponent implements OnInit {
  @Output() authSuccess = new EventEmitter<any>();
  public accessMenu = [];
  public username = "";
  public access = "requesting";

  constructor(
    public loginService: LoginService,
    public router: Router,
    public pageAccessService: PageAccessCheckService
  ) {
    this.accessMenu = this.loginService.accessMenu;
  }

  ngOnInit(): void {
    this.loginService.userInfo(
      (response) => {
        this.accessMenu = response.body.accessMenu;
        this.username = response.body.user.username;
        this.pageAccessService
          .checkPageAccess(response.body.accessMenu)
          .then((access) => {
            this.access = access ? "allow" : "deny";
            this.authSuccess.next(response);
          });
      },
      () => {
        this.router.navigate(["/"]);
      }
    );
  }

  logout() {
    this.loginService.logoutUser();
    this.router.navigate(["/"]);
  }
}
