import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Todo} from '../../../models/todo';
import {ToastrService} from 'ngx-toastr';
import {TodoService} from '../../../services/todo.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-todo-create',
  templateUrl: 'todo-create.component.html',
  providers: [TodoService]
})
export class TodoCreateComponent implements OnInit{

  @Output() todoSuccessEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();
  todo: Todo = new Todo();

  constructor(private todoService: TodoService, private toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  save(): void {
    this.todoService.saveTodo(this.todo).subscribe(
      todo => {
        // TODO show toast message and handle response by respone status
        if (todo.id) {
          this.toastr.success('Todo created successfully', 'Todo');
        } else {
          this.toastr.error('Todo creation failed', 'Todo');
        }
        this.router.navigate([`/todo-item/`]);
      },
      error => this.toastr.error('Todo creation failed', 'Todo')

    );
  }
}
