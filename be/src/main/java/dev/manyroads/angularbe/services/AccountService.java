package dev.manyroads.angularbe.services;

import dev.manyroads.angularbe.model.Customer;
import dev.manyroads.angularbe.projectioninterface.AccountCustomer;

import java.util.List;

public interface AccountService  {

    List<AccountCustomer> findCustomersProjected();
    List<Customer> findCustomers();

}
