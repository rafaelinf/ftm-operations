package com.financial.transaction.operations.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.financial.transaction.operations.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String>{

}
