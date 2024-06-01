
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TestDTO } from '../models/test-dto';
import { Account, AccountDTO } from '../types/account';

/**
 * Class that offers account REST communication to back end(BE) by way of individual functions
 */
@Injectable({ providedIn: 'root', })
export class AccountApiService {

    private apiUrl: string
    private apiUri: string = ""

    constructor(private http: HttpClient) {
        this.apiUrl = "http://localhost:8080"
    }

    /**
     * Function which sends out get method for testing the back end and receives string back
     * @returns  string : Test response from BE
     */
    getAccountTest(): Observable<TestDTO> {

        // Compose uri
        this.apiUri = `${this.apiUrl}/account-test`

        return this.http.get<TestDTO>(this.apiUri)
    }

    /**
     * Function using get method to BE and receives array of accounts back
     * @returns Account[] : Array of accounts
     */
    getAccounts(): Observable<Account[]> {

        // Compose uri
        this.apiUri = `${this.apiUrl}/accounts-short`

        return this.http.get<Account[]>(this.apiUri)
    }
    /**
     * Function gets Account by Id and receives account back
     * @returns Account[] : Array of accounts
     */
    getAccountById(id: string | null): Observable<Account> {

        // Compose uri
        this.apiUri = `${this.apiUrl}/account/${id}`

        console.log("apiUri ", this.apiUri)

        return this.http.get<Account>(this.apiUri)
    }
    /**
     * Function to deposit Account by Id and receives account balance back
     * @returns AccountDTO : Account DTO
     */
        depositAccountById(
            accountId: number | null,
            accountDTO: AccountDTO
        ): Observable<AccountDTO> {

            // Compose uri
            this.apiUri = `${this.apiUrl}/dto/deposit/${accountId}`
            //this.apiUri = `${this.apiUrl}/deposit/${accountId}`
    
            console.log("depositAccountById apiUri ", this.apiUri)
    
            return this.http.put<AccountDTO>(this.apiUri, accountDTO)
        }

        /**
     * Function to withdraw from Account by Id and receives account balance back
     * @returns AccountDTO : Account DTO
     */
        withdrawalAccountById(
            accountId: number | null,
            accountDTO: AccountDTO
        ): Observable<AccountDTO> {

            // Compose uri
            this.apiUri = `${this.apiUrl}/dto/withdrawal/${accountId}`
    
            console.log("withdrawalAccountById apiUri ", this.apiUri)
    
            return this.http.put<AccountDTO>(this.apiUri, accountDTO)
        }
}