import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Todo } from './todo.model';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private readonly TODO_BASE_URL = `${environment.BASE_URL}/todos`;

  constructor(private http: HttpClient) { }

  getAllTodos(): Observable<any> {
    return this.http.get<any>(this.TODO_BASE_URL);
  }

  createTodo(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>(this.TODO_BASE_URL, todo);
  }

  getTodoById(id: number): Observable<Todo> {
    return this.http.get<Todo>(`${this.TODO_BASE_URL}/${id}`);
  }

  updateTodo(updateTodo: Todo): Observable<Todo> {
    return this.http.put<Todo>(`${this.TODO_BASE_URL}/${updateTodo.id}`,updateTodo);
  }

  deleteTodo(id: number): Observable<Todo> {
    return this.http.delete<Todo>(`${this.TODO_BASE_URL}/${id}`);
  }
  
}
