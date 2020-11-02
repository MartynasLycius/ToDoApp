import {Component, OnInit, ViewChild} from '@angular/core';
import {ItemService} from '../services/item.service';
import {NgForm, NgModel} from '@angular/forms';
import {DatePipe} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';
import {Item} from '../model/item';
import {NgbCalendar, NgbDate, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.css']
})
export class UpdateItemComponent implements OnInit {

  item: Item  ;
  itemId: number;
  public model: NgbDateStruct;
  public date: {year: number, month: number};


  constructor(
    private  itemService: ItemService,
    private calendar: NgbCalendar,
    private datepipe: DatePipe,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.item = new Item();
    this.route.params.subscribe(params => {
      this.itemId = params.id;
      if (this.itemId) {
        this.itemService.getItem(this.itemId).subscribe(
          response => {
            this.item = response;
            const year =  Number(this.datepipe.transform(this.item.dueDate, 'yyyy'));
            const month =  Number(this.datepipe.transform(this.item.dueDate, 'MM'));
            const day =  Number(this.datepipe.transform(this.item.dueDate, 'dd'));
            this.item.dueDate = new NgbDate(year, month, day);
            console.log(this.item);
          }, error => {
            console.log(error);

          }
        );
      } else {
        this.item.dueDate = new Date();
      }
    });

  }


  createItem(ngForm: NgForm): void {
    const date = this.item.dueDate;
    this.item.dueDate = new Date(date.year, date.month, date.day);
    this.itemService.createItem(this.item)
      .subscribe( data => {
        alert('Item Updated successfully.');
      });

  }
}
