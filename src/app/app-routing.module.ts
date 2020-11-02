import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ItemListComponent} from './item-list/item-list.component';
import {AddItemComponent} from './add-item/add-item.component';
import {UpdateItemComponent} from './update-item/update-item.component';


const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'auth/login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: RegisterComponent
  },
  { path: 'view-item', component: ItemListComponent },
  { path: 'add-item', component: AddItemComponent },
  { path: 'update-item/:id', component: UpdateItemComponent },
  {
    path: '',
    redirectTo: 'view-item',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
