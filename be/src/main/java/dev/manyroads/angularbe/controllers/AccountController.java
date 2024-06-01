package dev.manyroads.angularbe.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dev.manyroads.angularbe.mappers.AccountMapper;
import dev.manyroads.angularbe.model.Account;
import dev.manyroads.angularbe.model.AccountDTO;
import dev.manyroads.angularbe.projectioninterface.AccountCustomer;
import dev.manyroads.angularbe.repository.AccountRepository;
import dev.manyroads.angularbe.repository.CustomerRepository;
import dev.manyroads.angularbe.services.AccountLogic;
import dev.manyroads.angularbe.services.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest controller for all account end-points
 */
@CrossOrigin
@RestController
public class AccountController {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;

    AccountServiceImpl service;
    AccountLogic accountLogic;
    AccountMapper mapper;

    public AccountController(AccountRepository accountRepository, CustomerRepository customerRepository, AccountServiceImpl service, AccountLogic accountLogic, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.service = service;
        this.accountLogic = accountLogic;
        this.mapper = mapper;
    }

    /**
     * Method for testing the FE-BE communication
     *
     * @return
     */
    @GetMapping("/account-test")
    public ResponseEntity pingTest() {


        System.out.println("In account pingTest");
        Map<String, String> response = new HashMap<>();
        response.put("response", "account ping works!!");

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for retrieving all projected accounts
     *
     * @return
     */
    @GetMapping("/accounts-short")
    public ResponseEntity<List<AccountCustomer>> getProjectedAccount() {

        System.out.println("In getProjectedAccount");

        return ResponseEntity.ok(accountRepository.findAllProjectedBy());
    }

    /**
     * Method for retrieving all accounts
     *
     * @return
     */
    @GetMapping("/accounts")
    public ResponseEntity<Iterable<Account>> getAccounts() {

        System.out.println("In getAccounts");


        return ResponseEntity.ok(accountRepository.findAllByOrderByAccountIdAsc());
        //return ResponseEntity.ok(accountRepository.findAll());
        //return ResponseEntity.ok(repo.findAllProjectedBy());
    }

    // ********************************** Testing ZonedDateTime ***************************************
    /*
    @GetMapping("/store-zdt")
    public ResponseEntity<ZonedDateTime> storeZDT() {

        System.out.println("In storeZDT");

        ZoneId z = ZoneId.of("US/Eastern");
        Instant i = Instant.now();
        System.out.println("i " + i);        // time UTC
        ZonedDateTime zdt = i.atZone(z);
        System.out.println("zdt " + zdt);    // time US/Eastern
        Account account = new Account();
        account.setBalance(new BigDecimal(456));
        account.setCreatedAt(zdt);
        Account accountSaved = accountRepository.save(account);
        System.out.println("accountSaved " + accountSaved.getCreatedAt().getZone());

        return ResponseEntity.ok(accountSaved.getCreatedAt());
    }
     */
    // ******************************************** END Testing ********************************************


    @GetMapping("/account/{id}")
    public ResponseEntity<String> getAccountById(@PathVariable long id) {

        System.out.println("In getAccountById " + id);

        Account accountOut = accountRepository.findById(id).get();

        //System.out.println("\nAccount details :: \n" + accountOut);
        //System.out.println("\nZone :: \n" + accountOut.getCreatedAt().getZone());

        //ObjectMapper objectMapper = new ObjectMapper();
        // Builder to read ZonedDateTime,  Jackson-Datatype-JSR310 provides support for Java 8 Time.
        ObjectMapper objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String serializedAccount = "";
        try {
            serializedAccount = objectMapper.writeValueAsString(accountOut);
            System.out.println("\nserializedAccount :: \n\n" + serializedAccount);
        } catch (JsonProcessingException ex) {
            System.out.println("\nFoutje mapper Account \n" + ex.getMessage());
        }
        System.out.println("Done");
        return ResponseEntity.ok(serializedAccount);
 /*
        if (account.isPresent()) {
            System.out.println("account: " + account.get());
            return ResponseEntity.ok(account.get());


            try {
                AccountDTO accountDTO = mapper.entityToApi(account.get());
                System.out.println("accountDTO: " + accountDTO);
                return ResponseEntity.ok(accountDTO);
            } catch (Exception ex) {
                System.out.println("Mapper foutje " + ex.getMessage());
            }
            //return ResponseEntity.ok(repo.findAllProjectedBy());


        }
        return ResponseEntity.internalServerError().body(new Account());
        */
    }

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<AccountDTO> depositAccountById(
            @PathVariable long accountId,
            @RequestBody AccountDTO amount) {

        // Variables
        AccountDTO accountDTO = new AccountDTO();

        System.out.println("depositAccountById " + amount.getAmount());

        // Convert deposit to BigDecimal
        BigDecimal depositIn = new BigDecimal(amount.getAmount());

        // Deposit account and return balance
        BigDecimal balance = accountLogic.depositAccountById(accountId, depositIn);

        // Prepare and return account DTO
        accountDTO.setAmount(balance.toPlainString());
        return ResponseEntity.ok(accountDTO);
    }

}
