package com.daedal00.app.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private String plaidAccessToken;
    private String plaidItemId;
    private List<BankAccount> linkedBankAccounts;
    private Date lastUpdated;


    public User() {}

    public User(String email, String username, String password, String firstName, String lastName) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Data
    public class BankAccount {
        private String accountId; // Plaid's account ID
        private String accountName;
        private String accountType;
    }
}
