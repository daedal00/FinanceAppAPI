package com.daedal00.app.api.dto;

public class TransactionDTO {

    private String id;
    private String userId;
    private String description;
    private Double amount;
    private String transactionType; 
    private String transactionDate;

    public TransactionDTO() {}

    public TransactionDTO(String id, String userId, String description, Double amount, 
                           String transactionType, String transactionDate) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
