import { Component } from '@angular/core';
import { Http, URLSearchParams, Headers }  from '@angular/http';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    name = '';
    users = [];
    result = '';
    user = {};

    constructor(private http: Http) {
        this.search()
    }

    // 検索
    search() {
        console.log(this.name)
        this.http.get('http://localhost:8080/', {
            params: { name: this.name }
        }).subscribe(
            response => {
                this.users = response.json();
                console.dir(response.json())
            },
            error => {
                this.result = `通信失敗：${error.statusText}`;
            }
        )
    }

    // 検索
    create() {
        console.log(this.name)
        this.http.post('http://localhost:8080/', this.user).subscribe(
            response => {
                this.users = response.json();
                console.dir(response.json())
            },
            error => {
                this.result = `通信失敗：${error.statusText}`;
            }
        )
    }
}
