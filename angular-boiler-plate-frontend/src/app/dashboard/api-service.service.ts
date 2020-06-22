import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "./../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class ApiServiceService {
  constructor(private http: HttpClient) {}

  addUser(data, success, error) {
    data.firstName = "n/a";
    data.lastName = "n/a";
    data.enabled = true;
    data.changePassword = true;
    this.http
      .post(environment.baseUrl + "/api/users", data, { observe: "response" })
      .subscribe({
        next: (res) => {
          success(res);
        },
        error: (err) => {
          error(err);
        },
      });
  }

  userList(success, error) {
    this.http
      .get(environment.baseUrl + "/api/users", { observe: "response" })
      .subscribe({
        next: (res) => {
          success(res);
        },
        error: (err) => {
          error(err);
        },
      });
  }

  addTodo(data, success, error) {
    this.http
      .post(environment.baseUrl + "/api/todos", data, {
        observe: "response",
      })
      .subscribe({
        next: (res) => {
          success(res);
        },
        error: (err) => {
          error(err);
        },
      });
  }

  updateTodo(data, success, error) {
    this.http
      .put(environment.baseUrl + "/api/todos/" + data.id, data, {
        observe: "response",
      })
      .subscribe({
        next: (res) => {
          success(res);
        },
        error: (err) => {
          error(err);
        },
      });
  }

  todoList(success, error) {
    this.http
      .get(environment.baseUrl + "/api/todos", { observe: "response" })
      .subscribe({
        next: (res) => {
          success(res);
        },
        error: (err) => {
          error(err);
        },
      });
  }

  getTodo(id, success, error) {
    this.http
      .get(environment.baseUrl + "/api/todos/" + id, { observe: "response" })
      .subscribe({
        next: (res) => {
          success(res);
        },
        error: (err) => {
          error(err);
        },
      });
  }

  deleteTodo(id, success, error) {
    this.http
      .delete(environment.baseUrl + "/api/todos/" + id, { observe: "response" })
      .subscribe({
        next: (res) => {
          success(res);
        },
        error: (err) => {
          error(err);
        },
      });
  }
}
