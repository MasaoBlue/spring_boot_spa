import { ModuleWithProviders }   from '@angular/core';
import { Routes, RouterModule }   from '@angular/router';

import { TaskComponent }  from './task.component';
import { UserComponent } from "./user.component";
import { ErrorComponent } from './error.component';

const myRoutes = [
  { path: 'user', component: UserComponent },
  { path: '', component: TaskComponent },
  { path: '**', component: ErrorComponent }
];

export const MY_ROUTES: ModuleWithProviders = RouterModule.forRoot(myRoutes);