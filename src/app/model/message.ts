import {Item} from './item';

export class Message {
  content: Item[];
  totalPages: number;
  pageNumber: number;
  pageSize: number;
}
