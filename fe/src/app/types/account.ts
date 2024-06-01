interface AccountMovement {
    description: string
}

export interface Account {
    accountId: number
    title: string
    balance: number
    currency: string
    createdAt: Date
}

// Interface to send an amount(deposit/withdrawal/balance) to and fro the BE
export interface AccountDTO {
    amount: number | undefined |null
    messageToUser: string
}
