export interface Page<T> {
  content: T[];
  last: boolean;
  totalPages: number;
  totalElements: number;
  first: boolean;
  sort: {
    sorted: boolean;
    unsorted: boolean;
    empty: boolean;
  };
  numberOfElements: number;
  size: number;
  number: number;
  empty: boolean;
}
