package com.daedal00.app.service;

import com.daedal00.app.model.PlaidData;
import com.daedal00.app.repository.AccountRepository;
import com.daedal00.app.repository.PlaidDataRepository;
import com.daedal00.app.repository.TransactionRepository;
import com.daedal00.app.model.Transaction;
import com.plaid.client.model.AccountBase;
import com.plaid.client.model.AccountsGetRequest;
import com.plaid.client.model.AccountsGetResponse;
import com.plaid.client.model.ItemPublicTokenExchangeRequest;
import com.plaid.client.model.ItemPublicTokenExchangeResponse;
import com.plaid.client.model.TransactionsGetRequest;
import com.plaid.client.model.TransactionsGetResponse;
import com.plaid.client.request.PlaidApi;

import retrofit2.Response;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaidService {

    @Autowired
    private PlaidDataRepository plaidDataRepository;

    @Autowired
    private PlaidApi plaidClient;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public String getAccessToken(String userId) {
        PlaidData plaidData = plaidDataRepository.findByUserId(userId);
        return plaidData != null ? plaidData.getAccessToken() : null;
    }

    public void setAccessToken(String userId, String accessToken) {
        PlaidData plaidData = plaidDataRepository.findByUserId(userId);
        if (plaidData == null) {
            plaidData = new PlaidData();
            plaidData.setUserId(userId);
        }
        plaidData.setAccessToken(accessToken);
        plaidDataRepository.save(plaidData);
    }

    public String getItemId(String userId) {
        PlaidData plaidData = plaidDataRepository.findByUserId(userId);
        return plaidData != null ? plaidData.getItemId() : null;
    }

    public void setItemId(String userId, String itemId) {
        PlaidData plaidData = plaidDataRepository.findByUserId(userId);
        if (plaidData == null) {
            plaidData = new PlaidData();
            plaidData.setUserId(userId);
        }
        plaidData.setItemId(itemId);
        plaidDataRepository.save(plaidData);
    }

    public String exchangePublicToken(String publicToken) throws IOException {
        ItemPublicTokenExchangeRequest request = new ItemPublicTokenExchangeRequest().publicToken(publicToken);
        Response<ItemPublicTokenExchangeResponse> response = plaidClient.itemPublicTokenExchange(request).execute();
    
        if (response.isSuccessful()) {
            return response.body().getAccessToken();
        } else {
            throw new IOException("Failed to exchange public token: " + response.errorBody().string());
        }
    }
    

    public List<AccountBase> fetchAccountsFromPlaid(String userId) throws IOException {
        String accessToken = getAccessToken(userId);
        AccountsGetRequest request = new AccountsGetRequest().accessToken(accessToken);
        Response<AccountsGetResponse> response = plaidClient.accountsGet(request).execute();
        return response.body().getAccounts();
    }

    
    LocalDate startLocalDate = LocalDate.parse("2021-01-01");
    LocalDate endLocalDate = LocalDate.parse("2023-01-01");

    public List<com.daedal00.app.model.Transaction> fetchTransactionsFromPlaid(String userId) throws IOException {
        String accessToken = getAccessToken(userId);
        TransactionsGetRequest request = new TransactionsGetRequest()
                .accessToken(accessToken)
                .startDate(startLocalDate)  // Example date, adjust as needed
                .endDate(endLocalDate);   // Example date, adjust as needed
        Response<TransactionsGetResponse> response = plaidClient.transactionsGet(request).execute();
        List<com.daedal00.app.model.Transaction> transactions = response.body().getTransactions().stream()
                .map(t -> new com.daedal00.app.model.Transaction(
                    t.getAccountId(),
                    userId,
                    t.getAmount(),
                    t.getCategory().get(0), // Assuming you want the primary category
                    t.getName(),
                    Date.valueOf(t.getDate())
                ))
                .collect(Collectors.toList());
        transactionRepository.saveAll(transactions);
        return transactions;
    }
}
