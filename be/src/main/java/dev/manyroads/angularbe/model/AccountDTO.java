package dev.manyroads.angularbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class AccountDTO {

    String amount;

    public AccountDTO() {
        this.amount = "<nothingToSeeHer>";
    }

    public AccountDTO(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "amount='" + amount + '\'' +
                '}';
    }
}
