package dev.manyroads.angularbe.services;

import dev.manyroads.angularbe.model.Account;
import dev.manyroads.angularbe.repository.AccountRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountLogic {

    AccountServiceImpl service;
    AccountRepository repository;

    public AccountLogic(AccountServiceImpl service, AccountRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    /**
     * Method to deposit the account. Find account by id, deposit and return balance to caller
     * <p>
     * accountId long               : id of account
     * amount BigDecimal            : amount to be deposit
     * return balance BigDecimal    : return balance of account
     */
    public BigDecimal depositAccountById(long accountId, BigDecimal amount) {

        Account accountIn = getAccount(accountId);

        // Deposit account
        accountIn.deposit(amount);

        // Save account
        Account accountOut = repository.save(accountIn);

        // Return balance
        return accountOut.getBalance();
    }

    /**
     * Method to withdraw from the account. Find account by id, withdraw and return balance to caller
     * <p>
     * @param accountId long    : id of account
     * @param amount BigDecimal : amount to be withdrawn
     * @return BigDecimal       : return account balance
     */
    public BigDecimal withdrawalAccountById(long accountId, BigDecimal amount) {

        Account accountIn = getAccount(accountId);

        // Deposit account
        accountIn.withdraw(amount);

        // Save account
        Account accountOut = repository.save(accountIn);

        // Return account balance
        return accountOut.getBalance();
    }
    // *** Sub methods ***

    /**
     * Method to retrieve account by account id
     * @param accountId
     * @return
     */
    @NotNull
    private Account getAccount(long accountId) {

        // Find account
        Optional<Account> account = repository.findById(accountId);
        // If not found throw exception
        account.orElseThrow(()-> new NoSuchElementException(String.format("Account id %d not found. ", accountId) ));

        // Return retrieved account
        return account.get();
    }
}
