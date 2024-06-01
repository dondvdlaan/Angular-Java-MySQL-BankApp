package dev.manyroads.angularbe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//@Entity
public class AccountMovement {

    @Id
    @GeneratedValue
    Long accMvmtId;
    String description;

    public AccountMovement() {
        this.description = "<nothingToSeeHere>";
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String
    toString() {
        return "AccountMovement{" +
                ", description='" + description + '\'' +
                '}';
    }
}
