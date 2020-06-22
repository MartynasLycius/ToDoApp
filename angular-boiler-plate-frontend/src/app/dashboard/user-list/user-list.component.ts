import { Component, OnInit } from "@angular/core";
import { ApiServiceService } from "./../api-service.service";

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
  styleUrls: ["./user-list.component.css"]
})
export class UserListComponent implements OnInit {
  public users;

  constructor(public apiService: ApiServiceService) {}

  ngOnInit(): void {}

  authSuccess(event) {
    this.apiService.userList(
      response => {
        this.users = response.body;
      },
      error => {}
    );
  }
}
