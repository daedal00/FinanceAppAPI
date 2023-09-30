package com.daedal00.app.resources;

import com.daedal00.app.service.PlaidService;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/info")
@Produces(MediaType.APPLICATION_JSON)
public class InfoResource {
  private final List<String> plaidProducts;

  public InfoResource(List<String> plaidProducts) {
    this.plaidProducts = plaidProducts;
  }

  public static class InfoResponse {
    @JsonProperty
    private final String itemId;
    @JsonProperty
    private final String accessToken;
    @JsonProperty
    private final List<String> products;

    public InfoResponse(List<String> plaidProducts, String accessToken, String itemID) {
      this.products = plaidProducts;
      this.accessToken = accessToken;
      this.itemId = itemID;
    }
  }

  @POST
  public InfoResponse getInfo() {
    return new InfoResponse(plaidProducts, plaidService.getAccessToken(userId),
      PlaidService.itemID);
  }
}
