import { Component } from '@angular/core';
import { Http, URLSearchParams, Headers } from '@angular/http';

import { TaskService } from './task.service'

@Component({
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent {
  searchTitle = "";
  tasks = [];
  result = '';
  task = null;

  constructor(private http: Http, private taskService: TaskService) {
    this.clearCreateForm()
    this.search("")
  }

  // タスク作成フォームをクリアする
  clearCreateForm() {
    this.task = { title: "" };
  }

  // 検索
  search(title: string) {
    this.taskService.getTasks(title).subscribe(
      data => {
        this.tasks = data;
        console.dir(data);
      },
      error => {
        this.result = `通信失敗：${error.statusText}`;
      }
    )
  }

  // タスク作成フォームでEnter押下時の処理
  createFormKeyDown(event: any) {
    if (event.which == 13) {
      this.create()
    }
  }

  // 登録
  create() {
    this.taskService.create(this.task).subscribe(
      data => {
        this.clearCreateForm()
        console.dir(data)
        this.search(this.searchTitle)
      },
      error => {
        this.result = `通信失敗：${error.statusText}`;
      }
    )
  }

  // 完了ステータスの更新
  updateComplete(id: string, event: any) {
    this.taskService.updateComplete(id, event.target.checked).subscribe(
      data => {
        console.dir(data)
      },
      error => {
        this.result = `通信失敗：${error.statusText}`;
      }
    )
  }
}