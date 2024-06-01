import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { Observable } from 'rxjs';
import { CustomerName } from '../types/customer';
import { CustomerApiService } from '../services/customer.api.service';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterLink,
    RouterOutlet,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    CommonModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  customerName: Observable<CustomerName> = new Observable<CustomerName>

  constructor(
    private customerApiService: CustomerApiService) { }

  ngOnInit(): void {

    this.customerName = this.customerApiService.getCustomerNameById("1");
  }

}
