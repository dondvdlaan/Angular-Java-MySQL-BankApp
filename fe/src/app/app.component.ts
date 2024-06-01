import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import { NavbarComponent } from './navbar/navbar.component';


@Component({
  selector: 'app-root',
  imports: [
    UserComponent,
    RouterOutlet,
    RouterLink,
    NavbarComponent,
  ],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})

export class AppComponent {

  message = ""
  appCount = 0

  addCount(count: number) {
    console.log("in addCount: ", count)
    this.appCount = count
  }

}
