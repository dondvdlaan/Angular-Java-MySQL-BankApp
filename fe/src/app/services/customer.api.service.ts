
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TestDTO } from '../models/test-dto';
import { Account } from '../types/account';
import { Customer, CustomerName } from '../types/customer';

/**
 * Class that offers customer REST communication to back end(BE) by way of individual functions
 */
@Injectable({ providedIn: 'root', })
export class CustomerApiService {

    private apiUrl: string = ""
    private apiUri: string = ""

    constructor(private http: HttpClient) {
        this.apiUrl = "http://localhost:8080"
    }

    /**
     * Function which sends out get method for testing the back end and receives string back
     * @returns  string : Test response from BE
     */
    getCustomerTest(): Observable<TestDTO> {

        // Compose uri
        this.apiUri = `${this.apiUrl}/customer-test`

        return this.http.get<TestDTO>(this.apiUri)
    }

    /**
     * Function using get method to BE and receives array of customers back
     * @returns Account[] : Array of customers
     */
    getCustomers(): Observable<Customer[]> {

        // Compose uri
        this.apiUri = `${this.apiUrl}/customers-short`

        return this.http.get<Customer[]>(this.apiUri)
    }
    /**
     * Function gets Customer by Id and receives customer back
     * @returns Customer: Customer object
     */
    getCustomerById(id: string | null): Observable<Customer> {

        // Compose uri
        this.apiUri = `${this.apiUrl}/dto/customer/${id}`

        console.log("apiUri ", this.apiUri)

        return this.http.get<Customer>(this.apiUri)
    }
     /**
     * Function gets Customer name by Id and receives customer back
     * @returns Customer: Customer object
     */
     getCustomerNameById(id: string | null): Observable<CustomerName> {

        // Compose uri
        this.apiUri = `${this.apiUrl}/customer-name/${id}`

        console.log("apiUri ", this.apiUri)

        return this.http.get<CustomerName>(this.apiUri)
    }
}