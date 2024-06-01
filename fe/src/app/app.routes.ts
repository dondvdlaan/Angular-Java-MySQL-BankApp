import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { UserComponent } from './components/user/user.component';
import { HomeComponent } from './components/home/home.component';
import { ContactComponent } from './components/contact/contact.component';
import { CheckingAccountComponent } from './components/checking-account/checking-account.component';
import { accountDetailsResolver } from './resolvers/accountDetails.resolver';
import { TestFormComponent } from './components/test-form/test-form.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: 'home',
        title: "Hompje",
        component: HomeComponent,
    },
    {
        path: 'user',
        title: "gebruikeeerrs",
        component: UserComponent,
    },
    {
        path: 'contact',
        title: "Contact",
        component: ContactComponent,
    },
    {
        path: 'test-form',
        title: "Test Form",
        component: TestFormComponent,
    },
    {
        path: 'account/:id',
        title: "Checking Account",
        component: CheckingAccountComponent,
        resolve: { accountDetails: accountDetailsResolver }
    },
];
