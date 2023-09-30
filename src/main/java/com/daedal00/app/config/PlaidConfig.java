package com.daedal00.app.config;

import com.plaid.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PlaidConfig {
    @Bean
    public ApiClient apiClient(@Autowired PlaidProperties plaidProperties) {
        Map<String, String> apiKeys = new HashMap<>();
        apiKeys.put("clientId", plaidProperties.getClientId());
        apiKeys.put("plaidVersion", "2020-09-14"); 
        apiKeys.put("secret", plaidProperties.getSecret());

        ApiClient apiClient = new ApiClient(apiKeys);
        apiClient.setPlaidAdapter(plaidProperties.getEnvironmentUrl());

        return apiClient;
    }
}
