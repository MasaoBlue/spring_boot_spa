import { Component } from '@angular/core';
import { Http, URLSearchParams, Headers } from '@angular/http';

import { TaskService } from './task.service'

@Component({
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent {
  title = '';
  tasks = [];
  result = '';
  task = {};

  constructor(private http: Http, private taskService: TaskService) {
    this.search()
  }

  // 検索
  search() {
    this.taskService.getTasks(this.title).subscribe(
      data => {
        this.tasks = data;
        console.dir(data);
      },
      error => {
        this.result = `通信失敗：${error.statusText}`;
      }
    )
  }

  // 登録
  create() {
    this.taskService.create(this.task).subscribe(
      data => {
        console.dir(data)
      },
      error => {
        this.result = `通信失敗：${error.statusText}`;
      }
    )
  }
}