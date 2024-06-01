import { Component, Inject, LOCALE_ID } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { Observable } from 'rxjs';
import { CommonModule, JsonPipe, formatNumber } from '@angular/common';

import { TestDTO } from '../../models/test-dto';

import { AccountApiService } from '../../services/account.api.service';
import { CustomerApiService } from '../../services/customer.api.service';
import { RouterLink, RouterOutlet, Router } from '@angular/router';
import { Customer } from '../../types/customer';
import { Account } from '../../types/account';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    MatGridListModule,
    MatTableModule,
    MatButtonModule,
    RouterLink,
    RouterOutlet
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  testResponse!: Observable<TestDTO>
  //testResponse!: TestDTO
  /*account: Account[] =[ {
    accountId: "",
    accountMovement: [{ description: "" }]
  }]*/
  accounts: Account[] = []
  account: Observable<Account> = new Observable<Account>
  customer: Observable<Customer> = new Observable<Customer>
  // Array of column ids used in html table
  displayedColumns: string[] = ['accountId', 'title', 'balance', 'createdAt'];
  //displayedColumns: string[] = ['accountId', 'title', 'balance', 'createdAt'];
  clickedRows: Set<Account> = new Set<Account>();

  constructor(
    private accountApiService: AccountApiService,
    private customerApiService: CustomerApiService,
    private router: Router,
  ) {  }

  /**
   * Function to navigate to other page / Component
   * @param row 
   */
  navigateTo(row: any) {
    this.router.navigate(['/contact/' + row.accountId]);
  }

  ngOnInit(): void {

    console.log("in ngOnInit() ")
    //this.testResponse = this.apiService.getTest()
    //this.apiService.getTest().subscribe(res => this.testResponse = res)
    /* this.apiService.getMovements().subscribe(acc => {
 
       console.log("acc:", acc)
       this.account = acc
       console.log("account: ", this.account[0].accountMovement[0].description)
     })*/
    //this.accounts = this.accountApiService.getAccounts()
    this.account = this.accountApiService.getAccountById("1")
    this.customer = this.customerApiService.getCustomerById("1")
    
    // Await customer, copy accounts and sort by account id
    this.customer.subscribe(customer =>
      this.accounts = customer.accounts.sort((a, b) => a.accountId - b.accountId))
      console.log("accounts ", this.accounts)


  }

}
