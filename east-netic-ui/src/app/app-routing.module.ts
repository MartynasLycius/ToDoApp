import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './core/components/login/login.component';
import {RegistrationComponent} from './core/components/registration/registration.component';
import {TodoListComponent} from './core/components/todo/todo-list/todo-list.component';
import {TodoCreateComponent} from './core/components/todo/todo-create/todo-create.component';
import {TodoUpdateComponent} from './core/components/todo/todo-update/todo-update.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'registration',
    component: RegistrationComponent,
    data: {
      title: 'Registration Page'
    }
  },
  {
    path: 'todo-item',
    component: TodoListComponent,
    data: {
      title: 'Todo Item list page'
    }
  },
  {
    path: 'todo-item-create',
    component: TodoCreateComponent,
    data: {
      title: 'Todo Item Create page'
    }
  },
  {
    path: 'todo-item-update/:id',
    component: TodoUpdateComponent,
    data: {
      title: 'Todo Item Update page'
    }
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      preloadingStrategy: PreloadAllModules,
      paramsInheritanceStrategy: 'always',
      initialNavigation: 'enabled'
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
