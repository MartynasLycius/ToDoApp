import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {LocalStorageService} from 'ngx-store';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: 'header.component.html',
  styleUrls: ['header.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeaderComponent implements OnInit{

  isLoggedIn = false;

  constructor(private localStorage: LocalStorageService, private router: Router) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.localStorage.get('access_token') != null;
  }

  logout(): void {
    this.localStorage.clear();
    this.isLoggedIn = this.localStorage.get('access_token') != null;
    this.router.navigate(['login']);
  }
}
