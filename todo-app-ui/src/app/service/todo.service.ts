import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  data: any;

  constructor() {
  }

  setData(d: any) {
    this.data = d;
  }

  getData() {
    return this.data;
  }


  getFormattedDate(calendarDay: any) {
    const month = '' + calendarDay.month;
    const formatMonth = month.padStart(2, '0');
    const day = '' + calendarDay.day;
    const formatDay = day.padStart(2, '0');
    return  calendarDay.year + '-' + formatMonth + '-' + formatDay;
  }

}
