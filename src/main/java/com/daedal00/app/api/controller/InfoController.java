package com.daedal00.app.api.controller;

import com.daedal00.app.resources.InfoResource;
import com.daedal00.app.service.PlaidService;
import com.plaid.client.model.AccountBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plaid/info")
public class InfoController {

    @Autowired
    private PlaidService plaidService;

    @GetMapping("/accounts")
    public List<AccountBase> getAccounts(@RequestParam String userId) throws IOException {
        return plaidService.fetchAccountsFromPlaid(userId);
    }

    @GetMapping
    public InfoResource.InfoResponse getInfo(@RequestParam String userId) throws IOException {
        String accessToken = plaidService.getAccessToken(userId);
        String itemId = plaidService.getItemId(userId);
    
        // Fetch accounts from Plaid
        List<AccountBase> accountBases = plaidService.fetchAccountsFromPlaid(userId);
    
        // Extract account names or other details from the fetched accounts
        List<String> accountNames = accountBases.stream()
            .map(AccountBase::getName)
            .collect(Collectors.toList());
    
        return new InfoResource.InfoResponse(accountNames, accessToken, itemId);
    }  
}
