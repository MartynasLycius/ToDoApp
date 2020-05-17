import { Component, OnInit } from '@angular/core';
import { Todo } from '../todo.model';
import { TodoService } from '../todo.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  todos:Todo[] = [];

  modalRef: BsModalRef;

  constructor(private todoService: TodoService,
              private toastr: ToastrService,
              private router: Router,
              private modalService: BsModalService) { }

  ngOnInit() {
    this.getAllTodos();
  }

  getAllTodos():void{
    this.todoService.getAllTodos().subscribe(
			res => {
				  this.todos = res;
			},
			err => {
				this.toastr.error("Error occured in get all todos ",err);
			}
		);
  }

  createTodo(){
    const _todoCreateUrl = 'todo/create';
    this.router.navigateByUrl(_todoCreateUrl);
  }

  deleteTodo(id:number) : void {
    console.log("Delete todo id ",id);
    this.modalRef = this.modalService.show(ConfirmationDialogComponent, {class: 'modal-sm'});

    this.modalRef.content.onClose.subscribe(result => {
      console.log('results', result);
      if(result){
        // Todo Request delete service
        this.todoService.deleteTodo(id).subscribe(
          res =>{
            this.toastr.success('', 'Delete Successfully.');
            this.getAllTodos();
          },
          err => {
            console.log("Error occurred while delete todo ",id);
          }
        );
      }
    });
  }

}
