package com.daedal00.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daedal00.app.model.Account;
import com.daedal00.app.model.PlaidData;
import com.daedal00.app.model.Transaction;
import com.daedal00.app.repository.AccountRepository;
import com.daedal00.app.repository.PlaidDataRepository;
import com.daedal00.app.repository.UserRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaidDataRepository plaidDataRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }
    

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    public List<Account> getAccountsByUserId(String userId) {
        return accountRepository.findByUserId(userId);
    }
}
