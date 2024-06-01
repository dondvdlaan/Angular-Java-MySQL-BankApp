import { Account } from "./account";

export interface CustomerName {
    custId: string
    firstName: string
    lastName: string
}

export interface Customer extends CustomerName {
    accounts: Account[]
}