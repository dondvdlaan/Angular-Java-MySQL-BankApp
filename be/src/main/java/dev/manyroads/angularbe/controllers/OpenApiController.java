package dev.manyroads.angularbe.controllers;

import dev.manyroads.angularbe.mappers.CustomerMapper;
import dev.manyroads.angularbe.model.AccountDTO;
import dev.manyroads.angularbe.model.Customer;
import dev.manyroads.angularbe.repository.CustomerRepository;
import dev.manyroads.angularbe.services.AccountLogic;
import org.SwaggerCodeGen.api.DtoApi;
import org.SwaggerCodeGen.model.CustomerDto;
import org.SwaggerCodeGen.model.Dto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@CrossOrigin
@RestController
public class OpenApiController implements DtoApi {

    CustomerRepository customerRepository;
    AccountLogic accountLogic;
    CustomerMapper mapper;

    public OpenApiController(CustomerRepository customerRepository, AccountLogic accountLogic, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.accountLogic = accountLogic;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Dto> depositById(Integer accountId, Dto dto) {

        // Variables
        Dto dtoOut = new Dto();

        System.out.println("depositById " + dto.getAmount());

        // Convert deposit to BigDecimal
        BigDecimal depositIn = new BigDecimal(dto.getAmount());

        // Deposit account and return balance
        BigDecimal balance = accountLogic.depositAccountById(accountId, depositIn);

        // Prepare and return account DTO
        dtoOut.setAmount(balance.toPlainString());
        return ResponseEntity.ok(dtoOut);
        // return DtoApi.super.depositById(accountId, dto);
    }

    @Override
    public ResponseEntity<Dto> withdrawalById(Integer accountId, Dto dto) {

        System.out.println("withdrawalById accountId " + accountId + " Dto " + dto.getAmount());

        // Variables
        Dto dtoOut = new Dto();

        // Convert deposit to BigDecimal
        BigDecimal depositIn = new BigDecimal(dto.getAmount());

        // Deposit account and return balance
        BigDecimal balance = accountLogic.withdrawalAccountById(accountId, depositIn);

        // Prepare and return account DTO
        dtoOut.setAmount(balance.toPlainString());
        return ResponseEntity.ok(dtoOut);
    }

    // ************** Testing ******************
    @PutMapping("/dtoo/{accountId}")
    public String testDto (@PathVariable Integer accountId, @RequestBody Dto dto ){

        System.out.println("accountId " + accountId);
        System.out.println("dto amount " + dto.getAmount());
        return "ok";
    }
    // ************** END Testing ******************

    /**
     * Get customer by id, REST annotations generated in OpenApi interface
     * @param custId Numeric ID of the customer to get (required)
     * @return
     */
    @Override
    public ResponseEntity<CustomerDto> customerById(Integer custId) {

        System.out.println("In customerById " + custId);

        // *** Vars ***
        CustomerDto customerDtoOut = new CustomerDto();

        if (custId != null) {
            Optional<Customer> customerIn = customerRepository.findById((long)custId);
            if (customerIn.isPresent()) {

                // Pass customer to mapper
                customerDtoOut = mapper.apiToDto(customerIn.get());

                return ResponseEntity.ok(customerDtoOut);
            }

        }

        return ResponseEntity.badRequest().body(customerDtoOut);
    }
}
