package dev.manyroads.angularbe.projectioninterface;

import dev.manyroads.angularbe.model.Customer;

import java.time.ZonedDateTime;

/**
 * A projection interface to retrieve a subset of attributes of a class, in this case accountId and customer of
 *  class Account
 */
public interface AccountCustomer {

    Long getAccountId();
    double getBalance();
    ZonedDateTime getCreatedAt();
    //Customer getCustomer();
}
