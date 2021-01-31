import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Todo} from '../../../models/todo';
import {TodoService} from '../../../services/todo.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ToastrService} from 'ngx-toastr';
import {LocalStorageService} from 'ngx-store';
import { Router} from '@angular/router';

@Component({
  selector: 'app-todo-list',
  templateUrl: 'todo-list.component.html',
  providers: [TodoService],
  encapsulation: ViewEncapsulation.None,
})
export class TodoListComponent implements OnInit{

  todos: Todo[]  = [];
  closeResult = '';
  currentUserId: string;

  constructor(private service: TodoService, private modalService: NgbModal,
              private toastrService: ToastrService, private localStorage: LocalStorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.currentUserId = this.localStorage.get('user_id');
    this.getTodos();
  }

  getTodos(): void {
    this.service.getTodos().subscribe(
      todos => this.todos = todos.content,
      error => console.log(error)
    );
  }

  onClickUpdate(id: string): void {
    this.router.navigate([`/todo-item-update/${id}`]);
  }

  add(): void {
    this.router.navigate([`/todo-item-create/`]);
  }
}
