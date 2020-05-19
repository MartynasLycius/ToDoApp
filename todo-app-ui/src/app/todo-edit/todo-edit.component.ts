import {Component, OnInit} from '@angular/core';
import {TodoService} from '../service/todo.service';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

@Component({
  selector: 'app-todo-edit',
  templateUrl: './todo-edit.component.html',
  styleUrls: ['./todo-edit.component.css']
})
export class TodoEditComponent implements OnInit {

  id: any;
  itemDate: any;
  itemName: any;
  description: any;

  constructor(private todoService: TodoService,
              private httpClient: HttpClient,
              private router: Router,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    const edit = this.todoService.getData();
    if (edit) {
      this.id = edit.id;
      const dt = edit.itemDate.split('-');
      this.itemDate = {year: Number(dt[0]), month: Number(dt[1]), day: Number(dt[2])};
      this.itemName = edit.itemName;
      this.description = edit.description;
    } else {
      this.router.navigate(['/']);
    }
  }

  onClearForm(): void {
    this.itemDate = null;
    this.itemName = null;
    this.description = null;
  }

  onUpdateTodo() {
    const submitForm = {
      id: this.id,
      itemDate: this.todoService.getFormattedDate(this.itemDate),
      itemName: this.itemName,
      description: this.description,
    };
    this.httpClient.put(environment.apiUrl + '/rest/todo-item', submitForm)
      .subscribe(
        res => {
          if (res['message'] === 'success') {
            this.toast.success('Successfully Updated');
            this.onClearForm();
            this.router.navigate(['/']);
          }
        },
        msg => {
          console.error(`Error: ${msg.status} ${msg.statusText}`);
          this.toast.error('Failed to save !!!');
        },
      );
  }

  onBack() {
    this.router.navigate(['/']);
  }

}
