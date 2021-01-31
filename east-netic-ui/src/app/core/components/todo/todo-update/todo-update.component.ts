import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Todo} from '../../../models/todo';
import {ToastrService} from 'ngx-toastr';
import {TodoService} from '../../../services/todo.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-todo-update',
  templateUrl: 'todo-update.component.html',
  providers: [TodoService]
})
export class TodoUpdateComponent implements OnInit{

  @Output() todoSuccessEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();
  todo: Todo = new Todo();

  constructor(private todoService: TodoService, private toastr: ToastrService,
              private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getTodoById(this.route.snapshot.params.id);
  }

  private getTodoById(id: string): void {
    this.todoService.getTodoById(id).subscribe(
      (todo: Todo) => this.todo = todo,
      error => console.log(error)
    );
  }

  update(): void {
    this.todoService.updateTodo(this.todo).subscribe(
      todo => {
        // TODO show toast message and handle response by respone status
        if (todo.id) {
          this.toastr.success('Todo updated successfully', 'Todo');
        } else {
          this.toastr.error('Todo update failed', 'Todo');
        }
        this.router.navigate([`/todo-item/`]);
      },
      error => this.toastr.error('Todo update failed', 'Todo')
    );
  }
}
