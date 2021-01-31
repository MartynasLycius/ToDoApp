import {User} from './user';
import {Time} from '@angular/common';

export class Todo {
  id: string;
  userId: string;
  user: User;
  description: string;
  date: Time;
  dateShow: Time;
  title: string;
}
