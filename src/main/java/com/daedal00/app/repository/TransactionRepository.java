package com.daedal00.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daedal00.app.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String>{
    
}
