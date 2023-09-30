package com.daedal00.app.service;

import com.daedal00.app.model.PlaidData;
import com.daedal00.app.repository.PlaidDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaidService {

    @Autowired
    private PlaidDataRepository plaidDataRepository;
    
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
}
