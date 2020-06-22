import { Component, OnInit } from "@angular/core";
import { FormGroup, FormControl } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ApiServiceService } from "../api-service.service";

@Component({
  selector: "app-edit-todo",
  templateUrl: "./edit-todo.component.html",
  styleUrls: ["./edit-todo.component.css"],
})
export class EditTodoComponent implements OnInit {
  public form;
  public categories;
  public TodoId;

  constructor(
    public apiService: ApiServiceService,
    public router: Router,
    public route: ActivatedRoute
  ) {
    this.TodoId = route.snapshot.params["id"];
    this.form = new FormGroup({
      id: new FormControl(this.TodoId),
      title: new FormControl(""),
      description: new FormControl(""),
    });
  }

  ngOnInit(): void {}

  submit() {
    this.apiService.updateTodo(
      this.form.value,
      (response) => {
        this.router.navigate(["/dashboard/todos"]);
      },
      (error) => {}
    );
  }

  authSuccess(event) {
    this.apiService.getTodo(
      this.TodoId,
      (response) => {
        this.form = new FormGroup({
          id: new FormControl(response.body.id),
          title: new FormControl(response.body.title),
          description: new FormControl(response.body.description),
        });
      },
      (error) => {}
    );
  }
}
