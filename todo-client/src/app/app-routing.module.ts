import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TodoEditComponent } from './todos/todo-edit/todo-edit.component';
import { TodoListComponent } from './todos/todo-list/todo-list.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'todos',
    pathMatch: 'full'
  },
  {
    path: 'todos',
    component: TodoListComponent
  },
  {
    path: 'todo/create',
    component: TodoEditComponent
  },
  {
    path: 'edit/:id',
    component: TodoEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
