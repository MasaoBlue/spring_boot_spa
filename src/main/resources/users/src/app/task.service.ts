import { Injectable } from '@angular/core';
import { Http, URLSearchParams, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Task } from './task';

@Injectable()
export class TaskService {
  baseUrl: string = 'http://localhost:8080'

  constructor(private http: Http) {}

  getTasks(name: string): Observable<any> {
    return this.http.get(this.baseUrl + '/api/task', {
      params: { name: name }
    }).pipe(map(data => {
      return data.json();
    }))
  }
  
  create(task: Task): Observable<any> {
    return this.http.post(this.baseUrl + '/api/task', task).pipe(map(data => {
      return data.json();
    }));
  }
}
