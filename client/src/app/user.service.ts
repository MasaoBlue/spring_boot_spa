import { Injectable } from '@angular/core';
import { Http, URLSearchParams, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from './user';

@Injectable()
export class UserService {
  baseUrl: string = 'http://localhost:8080'

  constructor(private http: Http) {}

  getUsers(name: string): Observable<any> {
    return this.http.get(this.baseUrl + '/api/user', {
      params: { name: name }
    }).pipe(map(data => {
      return data.json();
    }))
  }

  create(user: User): Observable<any> {
    return this.http.post(this.baseUrl + '/api/user', user).pipe(map(data => {
      return data.json();
    }));
  }
}
