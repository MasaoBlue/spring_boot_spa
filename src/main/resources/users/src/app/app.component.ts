import { Component } from '@angular/core';
import { Http, URLSearchParams, Headers }  from '@angular/http';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    name = '';
    users = [];

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
}
