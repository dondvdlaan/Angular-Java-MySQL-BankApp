package dev.manyroads.angularbe.repository;

import dev.manyroads.angularbe.model.Account;
import dev.manyroads.angularbe.projectioninterface.AccountCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<AccountCustomer> findAllProjectedBy();
    List<Account> findAllByOrderByAccountIdAsc();
}

