package dev.manyroads.angularbe.projectioninterface;

import java.time.ZonedDateTime;

/**
 * A projection interface to retrieve a subset of attributes of a class, in this case customer name of
 *  class Customer
 */
public interface CustomerName {

    Long getCustId();
    String getFirstName();
    String getLastName();

}
