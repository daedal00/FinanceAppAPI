package com.daedal00.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.daedal00.app.model.User;
import com.daedal00.app.repository.UserRepository;
import com.daedal00.app.service.PlaidService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlaidServiceIntegrationTest {

    @Autowired
    private PlaidService plaidService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        // Create and save a mock user or any other necessary data to the database
        User mockUser = new User("newmail@mail.com", "good_user", "good_password", "Jack", "Ko");
        userRepository.save(mockUser);
    }

    @Test
    public void testGetAccessTokenIntegration() {
        User foundUser = userRepository.findByUsername("good_user");
        assertNotNull(foundUser, "User should not be null");

        String userId = foundUser.getId();

        // Call the method
        String accessToken = plaidService.getAccessToken(userId);

        // Assert the result
        assertNotNull(accessToken);
        // ... any other assertions based on actual response
    }

}
