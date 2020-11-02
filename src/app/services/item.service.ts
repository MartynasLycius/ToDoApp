import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private baseUrl = 'http://localhost:8080/todos';

  constructor(private http: HttpClient) { }

  getItemList(request): Observable<any> {
    const endpoint = this.baseUrl;
    const params = request;
    return this.http.get(endpoint, { params });
  }

  // tslint:disable-next-line:no-shadowed-variable
  createItem(Item: object): Observable<any> {
    return this.http.post(`${this.baseUrl}`, Item);
  }

  deleteItem(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete-item/${id}`);
  }

  getItem(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateItem(id: number, value: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/update-item/${id}`, value);
  }

}
