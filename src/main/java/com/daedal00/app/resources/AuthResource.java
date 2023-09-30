package com.daedal00.app.resources;

import java.io.IOException;
import com.plaid.client.request.PlaidApi;
import com.daedal00.app.service.PlaidService;
import com.plaid.client.model.AuthGetRequest;
import com.plaid.client.model.AuthGetResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import retrofit2.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    private final PlaidApi plaidClient;

    public AuthResource(PlaidApi plaidClient) {
        this.plaidClient = plaidClient;
    }

    @GET
    public AuthGetResponse getAccounts() throws IOException {
        AuthGetRequest request = new AuthGetRequest()
        .accessToken(PlaidService.accessToken);
        Response<AuthGetResponse> response = plaidClient
        .authGet(request)
        .execute();

        return response.body();
    }
}
