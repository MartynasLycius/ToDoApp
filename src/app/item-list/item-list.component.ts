import { Component, OnInit } from '@angular/core';
import {Item} from '../model/item';
import {ItemService} from '../services/item.service';
import { PageEvent } from '@angular/material/paginator';
import {Router} from '@angular/router';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  totalElements = 0;
  items: Item[] = [];
  loading: boolean;
  constructor(private itemService: ItemService, private router: Router) { }

  ngOnInit(): void {
    this.getItems({ page: '0', size: '10' });
  }

  private getItems(request): void {
    this.loading = true;
    this.itemService.getItemList(request)
      .subscribe(data => {
        this.items = data.content;
        this.totalElements = data.totalElements;
        this.loading = false;
      }, error => {
        this.loading = false;
      });
  }

  nextPage(event: PageEvent): void {
    const request = {
      page: '0',
      size: '10'
    };
    request.page = event.pageIndex.toString();
    request.size = event.pageSize.toString();
    this.getItems(request);
  }

  btnClick(id): void {
    this.router.navigateByUrl('/update-item/' + id);
  }
}
