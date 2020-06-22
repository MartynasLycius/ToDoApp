import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { HomeComponent } from "./dashboard/home/home.component";
import { AddUserComponent } from "./dashboard/add-user/add-user.component";
import { UserListComponent } from "./dashboard/user-list/user-list.component";
import { AddTodoComponent } from "./dashboard/add-todo/add-todo.component";
import { ShowTodoComponent } from "./dashboard/show-todo/show-todo.component";
import { EditTodoComponent } from "./dashboard/edit-todo/edit-todo.component";

const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "dashboard", component: HomeComponent },
  { path: "dashboard/users/add", component: AddUserComponent },
  { path: "dashboard/users", component: UserListComponent },
  { path: "dashboard/todos/add", component: AddTodoComponent },
  { path: "dashboard/todos", component: ShowTodoComponent },
  { path: "dashboard/todos/:id", component: EditTodoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
