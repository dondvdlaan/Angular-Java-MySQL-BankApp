package dev.manyroads.angularbe.controllers;

import dev.manyroads.angularbe.model.Customer;
import dev.manyroads.angularbe.projectioninterface.CustomerName;
import dev.manyroads.angularbe.repository.AccountRepository;
import dev.manyroads.angularbe.repository.CustomerRepository;
import dev.manyroads.angularbe.mappers.AccountMapper;
import dev.manyroads.angularbe.services.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Rest controller for all customer end-points
 */
@CrossOrigin
@RestController
public class CustomerController {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;

    AccountServiceImpl service;
    AccountMapper mapper;

    public CustomerController(AccountRepository accountRepository, CustomerRepository customerRepository, AccountServiceImpl service, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Method for testing the FE-BE communication
     *
     * @return
     */
    @GetMapping("/customer-test")
    public ResponseEntity pingTest() {


        System.out.println("In customer pingTest");
        Map<String, String> response = new HashMap<>();
        response.put("response", "customer ping works!!");

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for retrieving all customers
     *
     * @return
     */
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomersFromAccount() {

        System.out.println("In getCustomersFromAccount");

        return ResponseEntity.ok(service.findCustomers());
    }


    /**
     * Method to retrieve customer by id
     *
     * @param custId
     * @return
     */
    @GetMapping("/customer/{custId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long custId) {

        System.out.println("In getCustomerById");

        if (custId != null) {
            Optional<Customer> customerIn = customerRepository.findById(custId);
            if (customerIn.isPresent()) {
                Customer customerOut = customerIn.get();
                return ResponseEntity.ok(customerOut);
            }

        }

        return ResponseEntity.badRequest().body(new Customer());
    }

    /**
     * Method to retrieve customer name by id
     *
     * @param custId
     * @return
     */
    @GetMapping("/customer-name/{custId}")
    public ResponseEntity<CustomerName> getCustomerNameById(@PathVariable Long custId) {

        System.out.println("In getCustomerNameById");

        if (custId != null) {
            CustomerName customerNameIn = customerRepository.findCustomerByCustId(custId);
            try {
                return ResponseEntity.ok(customerNameIn);
            } catch (Exception ex) {
                System.out.println("Foutje CustomerName " + ex.getMessage());
            }
        }
        return ResponseEntity.badRequest().body(new Customer());
    }

}
