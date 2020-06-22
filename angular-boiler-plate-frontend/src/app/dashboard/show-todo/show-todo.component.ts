import { Component, OnInit } from "@angular/core";
import { ApiServiceService } from "../api-service.service";

@Component({
  selector: "app-show-todo",
  templateUrl: "./show-todo.component.html",
  styleUrls: ["./show-todo.component.css"],
})
export class ShowTodoComponent implements OnInit {
  public todos = [];

  constructor(public apiService: ApiServiceService) {}

  ngOnInit(): void {}

  authSuccess(event) {
    this.loadTodos();
  }

  loadTodos() {
    this.apiService.todoList(
      (response) => {
        this.todos = response.body;
      },
      (error) => {}
    );
  }

  deleteTodo(id) {
    this.apiService.deleteTodo(
      id,
      (response) => {
        this.loadTodos();
      },
      (error) => {}
    );
  }
}
