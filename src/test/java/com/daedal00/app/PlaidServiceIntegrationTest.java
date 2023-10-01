package com.daedal00.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.daedal00.app.service.PlaidService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlaidServiceIntegrationTest {

    @Autowired
    private PlaidService plaidService;

    @Test
    public void testGetAccessTokenIntegration() {
        // Use actual public token from Plaid's sandbox
        String publicToken = "56339f893c70b7a2375fafa9838cae";

        // Call the method
        String accessToken = plaidService.getAccessToken(publicToken);

        // Assert the result
        assertNotNull(accessToken);
        // ... any other assertions based on actual response
    }

    // ... similarly for other methods
}
