package dev.manyroads.angularbe.services;

import dev.manyroads.angularbe.model.Customer;
import dev.manyroads.angularbe.projectioninterface.AccountCustomer;
import dev.manyroads.angularbe.repository.AccountRepository;
import dev.manyroads.angularbe.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    // **** Methods ****
    @Override
    public List<AccountCustomer> findCustomersProjected() {
        return accountRepository.findAllProjectedBy();
    }

    @Override
    public List<Customer> findCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

}
