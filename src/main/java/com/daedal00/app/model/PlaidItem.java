package com.daedal00.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plaidItems")
public class PlaidItem {
     @Id
    private String id;
    private String accessToken;
    private String itemId;
    private String institutionId;

    public PlaidItem() {}

    public PlaidItem(String accessToken, String itemId, String institutionId) {
        this.accessToken = accessToken;
        this.itemId = itemId;
        this.institutionId = institutionId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getItemID() { return itemId; }
    public void setItemID(String itemId) { this.itemId = itemId; }

    public String getInsitutionId() { return institutionId; }
    public void setInsitutionId(String insitutionId) { this.institutionId = insitutionId; }

}
