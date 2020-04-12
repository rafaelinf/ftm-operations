package com.financial.transaction.operations.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.transaction.operations.dto.TransactionDTO;
import com.financial.transaction.operations.exceptions.FinancialTransactionManegementException;
import com.financial.transaction.operations.model.Transaction;
import com.financial.transaction.operations.service.TransactionService;

@RestController	
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
    @PostMapping("/save")
	public Transaction save(@Valid @RequestBody TransactionDTO transactionDTO) throws FinancialTransactionManegementException {
    	Transaction transactionDTOResult = this.transactionService.save(transactionDTO);
		return transactionDTOResult;
	}
    
}
