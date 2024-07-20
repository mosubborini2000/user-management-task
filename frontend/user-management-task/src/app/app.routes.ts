import { Routes } from '@angular/router';
import { UserComponent } from './Components/user/user.component';
import { AddUserComponent } from './Components/add-user/add-user.component';
import { EditUserComponent } from './Components/edit-user/edit-user.component';

export const routes: Routes = [

    {
        path: '',
        redirectTo: 'user',
        pathMatch: 'full'
    },
    {
        path: 'user',
        component: UserComponent
    },

    {
        path: 'add-user',
        component: AddUserComponent
    },
    {
        path: 'edit-user/:id',
        component: EditUserComponent
    },

];
