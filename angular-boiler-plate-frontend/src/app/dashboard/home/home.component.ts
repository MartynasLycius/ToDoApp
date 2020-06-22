import { Component, OnInit } from "@angular/core";
import { ApiServiceService } from "./../api-service.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent implements OnInit {
  constructor(public apiService: ApiServiceService, public router: Router) {}

  ngOnInit(): void {}

  authSuccess(event) {
    if (event.body.user.roles[0].name == "ROLE_USER") {
      this.router.navigate(["/dashboard/todos"]);
    } else if (event.body.user.roles[0].name == "ROLE_ADMIN") {
      this.router.navigate(["/dashboard/users"]);
    }
    console.log("authSuccess", event);
  }
}
