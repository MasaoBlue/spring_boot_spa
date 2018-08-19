import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { MY_ROUTES }   from './app.routing';

import { AppComponent } from './app.component';
import { TaskComponent }  from './task.component';
import { UserComponent } from "./user.component";
import { ErrorComponent } from './error.component';

import { TaskService } from './task.service';

@NgModule({
  declarations: [
    AppComponent,
    TaskComponent,
    UserComponent,
    ErrorComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MY_ROUTES,
  ],
  providers: [TaskService],
  bootstrap: [AppComponent]
})
export class AppModule { }
