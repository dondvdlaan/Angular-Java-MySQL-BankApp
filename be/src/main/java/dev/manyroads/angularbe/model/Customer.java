package dev.manyroads.angularbe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.manyroads.angularbe.projectioninterface.CustomerName;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Customer implements CustomerName {

    // *** Fields ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long custId;
    String firstName;
    String lastName;

    // mapping to json using jackson causes an infinite loop, use @JsonManagedReference to avoid
    @JsonManagedReference
    // Account table has to have a customer_id column which stores a foreign key to the Customer table.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    // *** Constructors ***
    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // *** Getters & Setters ***
    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    // *** Methods ***
    /**
     * Method to add an account to the customer, where you have to set both sides of the association, to
     * make this a bidirectional relationship between customer and account
     * @return void
     */
    public void addAccount(Account account){

        // Set instamce / this customer to account
        account.setCustomer(this);
        // ...then add account to account list
        accounts.add(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
