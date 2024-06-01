import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatCard, MatCardHeader } from '@angular/material/card';
import { MatCardContent } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { Observable, map } from 'rxjs';
import { AsyncPipe, JsonPipe, CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';

import { AccountApiService } from '../../services/account.api.service';
import { Account, AccountDTO } from '../../types/account';
import { TestDTO } from '../../models/test-dto';



@Component({
  selector: 'app-account-details',
  standalone: true,
  imports: [
    AsyncPipe, MatCard, MatCardContent, MatCardHeader,
    FormsModule, MatFormFieldModule, MatInputModule,
    ReactiveFormsModule,
    MatGridListModule,
    CommonModule
  ],
  templateUrl: './checking-account.component.html',
  styleUrl: './checking-account.component.css',
  providers: [
    // Sets options of mat-form-field
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } }
  ]
})
export class CheckingAccountComponent {

  // *** Constants and variables ***
  testResponse: Observable<TestDTO>
  account$: Observable<Account>
  accountId: number = 0
  accountDTO: AccountDTO = { 
    amount: 0,
    messageToUser: ""
  }
  accountDTO$: Observable<AccountDTO> = new Observable<AccountDTO>
  deposit: number = 0
  balance: number | null | undefined = 0
  messageToUser: string = "blub"

  // ReactiveForms
  withdrawalForm = new FormGroup({
    withdrawal: new FormControl(0, [Validators.required, Validators.min(0)]),
  });

  /**
  * Initialise vars
  * @param route 
  */
  constructor(
    // Inject the activated route service
    private activatedRoute: ActivatedRoute,
    private accountApiService: AccountApiService,
  ) {
    this.testResponse = new Observable<TestDTO>
    this.account$ = new Observable<Account>
  }

  /**
   * Function that runs at start up of this component
   */
  ngOnInit() {
    // Testing
    this.testResponse = this.accountApiService.getAccountTest()
    this.testResponse.subscribe(res => console.log("res: ", res))
    // END Testing

    // receives return value(account by id) from resolver inquiry to back-end
    this.account$ = this.activatedRoute.data.pipe(map(data => data['accountDetails']));

    // Pick the balance and account id from the account and store in class field balance for HTML usage
    this.account$.subscribe((ac: Account) => {
      console.log("ac bal ", ac.balance)
      console.log("ac id ", ac.accountId)
      this.accountId = ac.accountId
      this.balance = ac.balance
    })
  }

  // **** Functions ****
  /**
   * Function to collect deposit form from HTML
   * @param depositForm any : Form from HTML
   */
  onDeposit(depositForm: any) {

    console.log("depositForm.value ", depositForm.value)
    this.accountId = depositForm.value.accountId
    console.log("accountId ", this.accountId)

    // Prepare account DTO
    this.accountDTO = {
      amount: depositForm.value.deposit,
      messageToUser: ""
    }

    // Deposit account and receive new balance
    this.accountDTO$ = this.accountApiService.depositAccountById(this.accountId, this.accountDTO)

    // Pick the balance from the accountDTO and store in class field balance for HTML usage
    this.accountDTO$.subscribe((acDto: AccountDTO) => {
      console.log("acDto ", acDto.amount)
      this.balance = acDto.amount
    })
  }

  /**
   * Function to collect withdrawal form from HTML
   */
  onWithdrawal() {
    console.log("id ", this.accountId)
    console.log("withdrawalForm wd ", this.withdrawalForm.value.withdrawal)

    // Prepare account DTO
    this.accountDTO = {
      amount: this.withdrawalForm.value.withdrawal,
      messageToUser: ""
    }
    // Withdrawal from account and receive new balance
    this.accountDTO$ = this.accountApiService.withdrawalAccountById(this.accountId, this.accountDTO)

    // subscribe to account do
    this.accountDTO$.subscribe((acDto: AccountDTO) => {
      // Pick the balance from the accountDTO and store in class field balance for HTML usage
      console.log("acDto ", acDto.amount)
      this.balance = acDto.amount
    })

  }


}
