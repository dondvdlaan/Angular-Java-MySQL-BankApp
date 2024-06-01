import { inject } from "@angular/core";
import { ResolveFn } from "@angular/router";
import { AccountApiService } from "../services/account.api.service";
import { Account } from "../types/account";

/**
 * Function to resolve / load object(Account), to have available BEFORE component/AccountDetails) 
 * is loaded
 * @param route 
 * @param state 
 * @returns Account from BE server
 */
export const accountDetailsResolver: ResolveFn<Account> = (route, state) => {

    // Captures id from calling URI
    const accountId = route.paramMap.get('id');
    console.log("Resolver ", accountId)

    // starts retrieving account by id from back-end
    return inject(AccountApiService).getAccountById(accountId)
}