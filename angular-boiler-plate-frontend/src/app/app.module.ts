import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { LoginComponent } from "./login/login.component";
import { BaseLayoutComponent } from "./layout/base-layout/base-layout.component";
import { LoginService } from "./login/login.service";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TokenInterceptor } from "./login/token.interceptor";
import { RefreshTokenService } from "./login/refresh.token.service";
import { AuthService } from "./login/auth.service";
import { HomeComponent } from "./dashboard/home/home.component";
import { AddUserComponent } from "./dashboard/add-user/add-user.component";
import { UserListComponent } from "./dashboard/user-list/user-list.component";
import { AddTodoComponent } from "./dashboard/add-todo/add-todo.component";
import { ShowTodoComponent } from "./dashboard/show-todo/show-todo.component";
import { EditTodoComponent } from "./dashboard/edit-todo/edit-todo.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    BaseLayoutComponent,
    HomeComponent,
    AddUserComponent,
    UserListComponent,
    AddTodoComponent,
    ShowTodoComponent,
    EditTodoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    LoginService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    RefreshTokenService,
    AuthService,
  ],
  bootstrap: [AppComponent],
  exports: [ReactiveFormsModule],
})
export class AppModule {}
