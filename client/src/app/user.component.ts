import { Component } from '@angular/core';
import { Http, URLSearchParams, Headers }  from '@angular/http';

import { UserService } from './user.service'

@Component({
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent {
  searchName = '';
  users = [];
  result = '';
  user = null;

  constructor(private http: Http, private userService: UserService) {
    this.clearCreateForm()
    this.search("")
  }

  // ユーザ作成フォームをクリアする
  clearCreateForm() {
    this.user = { name: "", email: "" };
  }
  // 検索
  search(name: string) {
    this.userService.getUsers(name).subscribe(
      data => {
        this.users = data;
        console.dir(data);
      },
      error => {
        this.result = `通信失敗：${error.statusText}`;
      }
    )
  }

  // 検索
  create() {
    this.userService.create(this.user).subscribe(
      data => {
        this.clearCreateForm()
        console.dir(data)
        this.search(this.searchName)
      },
      error => {
        this.result = `通信失敗：${error.statusText}`;
      }
    )
  }
}
