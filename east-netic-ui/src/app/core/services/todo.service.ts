import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Todo} from '../models/todo';
import {TODO_URL} from '../constants/enpoints';
import {LocalStorageService} from 'ngx-store';
import {Page} from '../models/page';

@Injectable()
export class TodoService {

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) {
  }

  getTodos(): Observable<Page<Todo>>{
    const headers = { user_id: this.localStorageService.get('user_id') };
    return this.httpClient.get<Page<Todo>>(`${TODO_URL}/by-user-id`, {headers});
  }

  getTodoById(id: string): Observable<Todo>{
    return this.httpClient.get<Todo>(`${TODO_URL}/${id}`);
  }

  saveTodo(todo: Todo): Observable<Todo> {
    const builtTodo = this.setUserId(todo);
    return this.httpClient.post<Todo>(TODO_URL, builtTodo);
  }

  updateTodo(todo: Todo): Observable<Todo> {
    const builtTodo = this.setUserId(todo);
    return this.httpClient.put<Todo>(`${TODO_URL}/${todo.id}`, builtTodo);
  }

  setUserId(todo: Todo): Todo{
    todo.userId = this.localStorageService.get('user_id');
    return todo;
  }
}
