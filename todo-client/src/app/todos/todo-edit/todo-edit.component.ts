import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Todo } from '../todo.model';
import { TodoService } from '../todo.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TodoSerializer } from '../todo-serializer';

@Component({
  selector: 'app-todo-edit',
  templateUrl: './todo-edit.component.html',
  styleUrls: ['./todo-edit.component.css']
})
export class TodoEditComponent implements OnInit {

  private subscriptions: Subscription[] = [];
  
  todo: Todo;
  todoForm: FormGroup;
  hasFormErrors: boolean;

  readonly _todoListUrl = 'todos';

  pageTitle: string;

  minDate: Date;
  maxDate: Date;

  constructor(private fb: FormBuilder,
              private todoService: TodoService,
              private toastr: ToastrService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    const routeSubscription = this.route.params.subscribe( params => {
      const id = +params['id'];
      if(id && id > 0){
        this.getTodoById(id);
      }else{
        this.todo = new Todo();
        this.initTodo();
      }
    });
    this.subscriptions.push(routeSubscription);
  }

  ngOnDestroy() {
		this.subscriptions.forEach(sb => sb.unsubscribe());
	}

  initTodo(){
    this.createTodoForm();
    if(this.todo.id){
      this.minDate = new Date();
      this.pageTitle = "Update Todo";
    }else{
      this.minDate = new Date();
      this.pageTitle = "Create New Todo";
    }
  }

  createTodoForm() {
    this.todoForm = this.fb.group({
      title: [this.todo.title, Validators.required ],
      description: [this.todo.description],
      dueDate: [this.todo.dueDate]
    });
  }

  get f() { return this.todoForm.controls; }

  onSubmit() {
    this.hasFormErrors = false;

    if (this.todoForm.invalid) {

      Object.keys(this.f).forEach(controlName =>
        this.f[controlName].markAsTouched()
      );

      this.hasFormErrors = true;

      return;
    }

    const editedTodo = this.prepareTodo();

		if (editedTodo.id > 0) {
			this.updateRegistration(editedTodo);
			return;
		}
		this.createNewTodo(editedTodo);
  }

  /**
	 * Returns prepared data for save
	 */
  prepareTodo(): Todo {

    const _todo = new Todo();

    _todo.id = this.todo.id;
    _todo.title = this.f.title.value;
    _todo.description = this.f.description.value;
    _todo.dueDate = this.f.dueDate.value;

    return _todo;
  }

  onReset() {
      this.hasFormErrors = false;
      this.todoForm.reset();
  }

  /**
	 * Create New Todo
	 *
	 * @param _todo: Todo
	 */
	createNewTodo(_todo: Todo) {
		this.todoService.createTodo(_todo).subscribe(
			res => {
					this.todo.id = res.id;
          this.toastr.success("Todo create successfully");
          this.onReset();
          this.router.navigateByUrl(this._todoListUrl);
			},
			err => {
				this.toastr.error(err.message);
			}
		);
	}

	/**
	 * update Todo
	 *
	 * @param _todo: Todo
	 */
	updateRegistration(_todo: Todo) {
		this.todoService.updateTodo(_todo).subscribe(
			res => {
					this.todo.id = res.id;
          this.toastr.success("Todo update successfully");
          this.onReset();
          this.router.navigateByUrl(this._todoListUrl);
			},
			err => {
				this.toastr.error(err);
			}
		);
  }
  
  getTodoById(id: number):void{
    this.todoService.getTodoById(id).subscribe(
			res => {
        this.todo = new TodoSerializer().fromJson(res);
        this.initTodo();
			},
			err => {
				this.toastr.error("Error occured at get todo",err);
			}
		);
  }

  goToTodos(){
    const _todoListUrl = 'todos';
    this.router.navigateByUrl(_todoListUrl);
  }


}
