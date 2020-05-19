import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Router} from '@angular/router';
import {TodoService} from '../service/todo.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  todoList: any = [];
  todoDoneList: any = [];

  constructor(private httpClient: HttpClient,
              private router: Router,
              private toast: ToastrService,
              private todoService: TodoService) {
  }

  ngOnInit(): void {
    this.getTodoItemList();
    this.getTodoItemDoneList();
  }

  getTodoItemList(): void {
    this.httpClient.get(environment.apiUrl + '/rest/todo-item-list')
      .subscribe(
        res => {
          this.todoList = res;
        },
        msg => {
          console.error(`Error: ${msg.status} ${msg.message}`);
        }
      );
  }


  getTodoItemDoneList(): void {
    this.httpClient.get(environment.apiUrl + '/rest/todo-item-list/done')
      .subscribe(
        res => {
          this.todoDoneList = res;
        },
        msg => {
          console.error(`Error: ${msg.status} ${msg.message}`);
        }
      );
  }

  onEditTodoItem(todo: any) {
    this.todoService.setData(todo);
    this.router.navigate(['/todo-edit']);
  }

  onDoneTodo(todo: any) {
    const httpParams = new HttpParams()
      .append('id', todo.id);
    this.httpClient.put(environment.apiUrl + '/rest/todo-item/done', {}, {params: httpParams})
      .subscribe(
        res => {
          if (res['message'] === 'success') {
            this.toast.success('Successfully Done');
            this.ngOnInit();
          }
        },
        msg => {
          console.error(`Error: ${msg.status} ${msg.statusText}`);
          this.toast.error('Failed to save !!!');
        },
      );
  }

  onMakeTodo(todo: any) {
    const httpParams = new HttpParams()
      .append('id', todo.id);
    this.httpClient.put(environment.apiUrl + '/rest/todo-item/make-todo', {}, {params: httpParams})
      .subscribe(
        res => {
          if (res['message'] === 'success') {
            this.toast.success('Successfully Make Todo');
            this.ngOnInit();
          }
        },
        msg => {
          console.error(`Error: ${msg.status} ${msg.statusText}`);
          this.toast.error('Failed to save !!!');
        },
      );
  }
}
