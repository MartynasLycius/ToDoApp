import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ApiServiceService } from "../api-service.service";

@Component({
  selector: "app-add-todo",
  templateUrl: "./add-todo.component.html",
  styleUrls: ["./add-todo.component.css"],
})
export class AddTodoComponent implements OnInit {
  public form;

  constructor(public apiService: ApiServiceService, public router: Router) {
    this.form = new FormGroup({
      title: new FormControl(""),
      description: new FormControl(""),
    });
  }

  ngOnInit(): void {}

  submit() {
    this.apiService.addTodo(
      this.form.value,
      (response) => {
        this.router.navigate(["/dashboard/todos"]);
      },
      (error) => {}
    );
  }

  authSuccess(event) {}
}
