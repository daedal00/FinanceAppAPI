package com.daedal00.app.config;

import com.plaid.client.PlaidClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaidConfig {

    @Value("${plaid.client.id}")
    private String clientId;

    @Value("${plaid.client.secret}")
    private String secret;

    @Value("${plaid.client.publicKey}")
    private String publicKey;

    @Bean
    public PlaidClient plaidClient() {
        return PlaidClient.newBuilder()
            .clientIdAndSecret(clientId, secret)
            .publicKey(publicKey)
            .developmentBaseUrl() // Use this for development. Switch to other environments as needed.
            .build();
    }
}
