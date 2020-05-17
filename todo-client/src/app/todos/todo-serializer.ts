import { Todo } from './todo.model';
import * as moment from 'moment';

export class TodoSerializer {

    fromJson(json: any): Todo {

        const todo = new Todo();

        todo.id = json.id;
        todo.title = json.title;
        todo.description = json.description;
        todo.dueDate = json.dueDate ? moment(json.dueDate, 'YYYY-MM-DD').toDate() : null;

        return todo;
    }

    toJson(todo: Todo): any {
        return {
            id: todo.id,
            title: todo.title,
            description: todo.description,
            dueDate: todo.dueDate,
        };
    }

    toArray(json: any): Todo[] {

        let todoList: Todo[] = [];
        if (!json) return todoList;

        json.forEach(element => {
            todoList.push(this.fromJson(element));
        });

        return todoList;
    }

}
