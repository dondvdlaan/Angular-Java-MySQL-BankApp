package dev.manyroads.angularbe.simulation;

import dev.manyroads.angularbe.mappers.AccountMovementMapper;
import dev.manyroads.angularbe.mappers.CustomerMapper;
import dev.manyroads.angularbe.model.*;
import dev.manyroads.angularbe.repository.AccountRepository;
import dev.manyroads.angularbe.repository.CustomerRepository;
import dev.manyroads.angularbe.mappers.AccountMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class FillDataBase implements CommandLineRunner {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;
    AccountMapper accountMapper;
    CustomerMapper customerMapper;
    AccountMovementMapper accountMovementMapper;

    public FillDataBase(AccountRepository accountRepository, CustomerRepository customerRepository, AccountMapper accountMapper, CustomerMapper customerMapper, AccountMovementMapper accountMovementMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
        this.customerMapper = customerMapper;
        this.accountMovementMapper = accountMovementMapper;
    }

    // @Transactional annotation which keeps the session open till the end of the execution
    @Transactional
    @Override
    public void run(String... args) throws Exception {

        // Fill only once
        if (accountRepository.count() == 0) {

            System.out.println("Creating DB");

            String currency = "EUR";

            // Create customer w 3 account
            Customer customer1 = new Customer("Aapie", "Staart");

            Account account1 = new Account("Checking account", new BigDecimal(123),new BigDecimal(100), currency);
            customer1.addAccount(account1);

            Account account2 = new Account("Savings account", new BigDecimal(5000),new BigDecimal(110), currency);
            customer1.addAccount(account2);
            Account account3 = new Account("Bouw financiering", new BigDecimal(6000),new BigDecimal(120), currency);
            customer1.addAccount(account3);

            customerRepository.save(customer1);

/*
            accountRepository.save(account1);
            accountRepository.save(account2);
            //account1.setCustomer(customer1);
            //account2.setCustomer(customer1);


          customer1.getAccounts().add(account1);
            customer1.getAccounts().add(account2);

            account1.setCustomer(customer1);
            account2.setCustomer(customer1);

            // First save account ManyToOne
            accountRepository.save(account1);
            accountRepository.save(account2);

            // .., then save customer OneToMany
            //System.out.println("accountSaved1: " + accountSaved1);



            Customer customer2 = new Customer("Patito", "Duck");
            //customer2.getAccounts().add(account2);
            //customerRepository.save(customer2);

            String timeNow = LocalTime.now().toString().substring(0, 13);
            AccountMovement accountMovement1 = new AccountMovement();
            accountMovement1.setDescription("Transfer_" + timeNow);
            accountMovementsRepository.save(accountMovement1);

            timeNow = LocalTime.now().toString().substring(0, 13);
            AccountMovement accountMovement2 = new AccountMovement();
            accountMovement2.setDescription("Transfer_" + timeNow);
            //accountMovementsRepository.save(accountMovement2);

            listAccountMovements1.add(accountMovement1);
            listAccountMovements1.add(accountMovement2);

            timeNow = LocalTime.now().toString().substring(0, 13);
            AccountMovement accountMovement3 = new AccountMovement();
            accountMovement3.setDescription("Transfer_" + timeNow);
            //accountMovementsRepository.save(accountMovement3);

            timeNow = LocalTime.now().toString().substring(0, 13);
            AccountMovement accountMovement4 = new AccountMovement();
            accountMovement4.setDescription("Transfer_" + timeNow);
            // accountMovementsRepository.save(accountMovement4);

            listAccountMovements2.add(accountMovement3);
            listAccountMovements2.add(accountMovement4);


            if (accountSaved1 != null && accountSaved2 != null) {

                accountSaved1.setCustomer(customerSaved1);
                accountRepository.save(accountSaved1);
                System.out.println("Saved accountSaved1");

                // accountSaved2.setAccountMovement(listAccountMovements2);
                // accountSaved2.setCustomer(customer2);

                //System.out.println("Saved savedaccount2: " + accountRepository.save(accountSaved2));
            }

 */
        } // END IF

    }
}
