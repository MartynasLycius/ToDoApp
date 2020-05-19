import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {TodoService} from "../service/todo.service";

@Component({
  selector: 'app-todo-create',
  templateUrl: './todo-create.component.html',
  styleUrls: ['./todo-create.component.css']
})
export class TodoCreateComponent implements OnInit {

  itemDate: any;
  itemName: any;
  description: any;

  constructor(private httpClient: HttpClient,
              private router: Router,
              private todoService: TodoService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.onClearForm();
  }

  onClearForm(): void {
    this.itemDate = null;
    this.itemName = null;
    this.description = null;
  }

  onCreateTodo() {
    const submitForm = {
      itemDate: this.todoService.getFormattedDate(this.itemDate),
      itemName: this.itemName,
      description: this.description,
    };
    this.httpClient.post(environment.apiUrl + '/rest/todo-item', submitForm)
      .subscribe(
        res => {
          if (res['message'] === 'success') {
            this.toastr.success('Successfully Saved');
            this.onClearForm();
            this.router.navigate(['/']);
          }
        },
        msg => {
          console.error(`Error: ${msg.status} ${msg.statusText}`);
          this.toastr.error('Failed to save !!!');
        },
      );
  }

  onBack() {
    this.router.navigate(['/']);
  }

}
