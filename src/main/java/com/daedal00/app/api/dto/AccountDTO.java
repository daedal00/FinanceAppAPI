package com.daedal00.app.api.dto;

public class AccountDTO {
    private String id;
    private String userId;
    private String name;
    private String institutionName;
    private String accountType;
    private Double balance;

    public AccountDTO() {}

    public AccountDTO(String id, String userId, String name, String institutionName, String accountType, Double balance) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.institutionName = institutionName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
