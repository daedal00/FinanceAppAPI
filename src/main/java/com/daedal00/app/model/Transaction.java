package com.daedal00.app.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction {
    
    @Id
    private String id;
    private String accountId;
    private String userId;
    private Double amount;
    private String category;
    private String merchantName;
    private Date date;

    public Transaction() {}

    public Transaction(String accountId, String userId, Double amount, String category, String merchantName, Date date) {
        this.accountId = accountId;
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.merchantName = merchantName;
        this.date = date;
    }

    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getUserID() { return userId; }
    public void setUserId(String userID) { this.userId = userID; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getMerchantName() { return merchantName; }
    public void setMerchantName(String merchantName) { this.merchantName = merchantName; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
