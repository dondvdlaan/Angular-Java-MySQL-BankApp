package dev.manyroads.angularbe.repository;

import dev.manyroads.angularbe.model.Customer;
import dev.manyroads.angularbe.projectioninterface.CustomerName;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    CustomerName findCustomerByCustId(long custId);
}
