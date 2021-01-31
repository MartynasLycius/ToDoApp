import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {LoginComponent} from './core/components/login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {RegistrationComponent} from './core/components/registration/registration.component';
import {TodoListComponent} from './core/components/todo/todo-list/todo-list.component';
import {TodoCreateComponent} from './core/components/todo/todo-create/todo-create.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {LocalStorageService} from 'ngx-store';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {HeaderComponent} from './core/components/header/header.component';
import {TodoUpdateComponent} from './core/components/todo/todo-update/todo-update.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    TodoListComponent,
    TodoCreateComponent,
    TodoUpdateComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [LocalStorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
