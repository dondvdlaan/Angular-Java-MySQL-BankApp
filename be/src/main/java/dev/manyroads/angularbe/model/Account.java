package dev.manyroads.angularbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Entity
public class Account implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long accountId;
    String title;
    BigDecimal balance;
    BigDecimal overdraftLimit;
    String currency;
    OffsetDateTime createdAt;

    // mapping to json using jackson causes an infinite loop, use @JsonBackReference to avoid
    @JsonBackReference
    // Customer can have more accounts, that is why many accounts to one customer
    @ManyToOne(cascade = CascadeType.ALL)
    // Account table has to have a customer_id column which stores a foreign key to the Customer table.
    @JoinColumn(name = "custId")
    Customer customer;

    /*
    // Account can have many movements
    @OneToMany(cascade = CascadeType.ALL)
    List<AccountMovement> accountMovements = new ArrayList<AccountMovement>();

     */

    public Account() {
        this.title = "<nothingToseeHere>";
        this.balance = new BigDecimal(0);
        this.createdAt = OffsetDateTime.now();
    }

    public Account(String title, BigDecimal balance,BigDecimal overdraftLimit, String currency) {
        this.title = title;
        this.balance = balance;
        // If overdraftLimit is neagtive, set to 0
        this.overdraftLimit = overdraftLimit.compareTo(BigDecimal.valueOf(0)) > 0 ? overdraftLimit : new BigDecimal(0);
        this.currency = currency;
        this.createdAt = OffsetDateTime.now();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", title='" + title + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                '}';
    }

    // **** Methods ****

    /**
     * Deposit amount
     *
     * @param amount BigDecimal    : amount to be deposited
     * @return balance BigDecimal  : adjusted balance
     */
    public BigDecimal deposit(BigDecimal amount) {

        // Check if amount is not negative
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Deposit has to be positive amount");
        else
            balance = balance.add(amount);

        return balance;
    }

    /**
     * Method to withdraw amount from balance
     *
     * @param amount double         : amount to be withdrawn
     * @return balance BigDecimal   : adjusted balance
     */
    public BigDecimal withdraw(BigDecimal amount) {

        // Check if amount is not negative
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Withdrawal has to be positive amount");
        else {
            // Check amount to be withdrawn against overdraft limit
            BigDecimal tempBalance = balance.subtract(amount);
            if (tempBalance.compareTo(overdraftLimit.negate()) > 0)
                balance = balance.subtract(amount);
            System.out.println(balance.toPlainString());
        }
        return balance;
    }
}
