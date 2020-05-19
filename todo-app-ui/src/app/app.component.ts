import {Component, OnInit} from '@angular/core';
import {TodoService} from './service/todo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Todo App';

  currentDate = new Date();

  currentYear: any;

  constructor(public todoService: TodoService) {
  }

  ngOnInit(): void {
    this.currentYear = this.currentDate.getFullYear();
  }
}
