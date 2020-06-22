import { Component, OnInit } from "@angular/core";
import { ApiServiceService } from "./../api-service.service";
import { FormGroup, FormControl } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: "app-add-user",
  templateUrl: "./add-user.component.html",
  styleUrls: ["./add-user.component.css"]
})
export class AddUserComponent implements OnInit {
  public form;
  constructor(public apiService: ApiServiceService, public router: Router) {
    this.form = new FormGroup({
      username: new FormControl(""),
      email: new FormControl(""),
      password: new FormControl("")
    });
  }

  ngOnInit(): void {}

  authSuccess(event) {
    console.log("authSuccess", event);
  }

  submit() {
    this.apiService.addUser(
      this.form.value,
      () => {
        this.router.navigate(["/dashboard/users"]);
      },
      () => {}
    );
  }
}
