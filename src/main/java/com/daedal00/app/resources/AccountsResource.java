package com.daedal00.app.resources;

import java.io.IOException;
import com.plaid.client.request.PlaidApi;
import com.daedal00.app.service.PlaidService;
import com.plaid.client.model.AccountsGetRequest;
import com.plaid.client.model.AccountsGetResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import retrofit2.Response;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountsResource {
    private final PlaidApi plaidClient;

    public AccountsResource(PlaidApi plaidClient) {
        this.plaidClient = plaidClient;
    }

    @GET
    public AccountsGetResponse getAccounts() throws IOException {
        AccountsGetRequest request = new AccountsGetRequest()
        .accessToken(PlaidService.accessToken);

        Response<AccountsGetResponse> response = plaidClient
        .accountsGet(request)
        .execute();
        return response.body();
    }
}
