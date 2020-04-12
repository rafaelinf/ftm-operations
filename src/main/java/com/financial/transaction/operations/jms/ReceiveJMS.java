package com.financial.transaction.operations.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.transaction.operations.dto.TransactionDTO;
import com.financial.transaction.operations.exceptions.FinancialTransactionManegementException;
import com.financial.transaction.operations.service.TransactionService;

@Service
public class ReceiveJMS {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveJMS.class);
	
	@Autowired
	private TransactionService transactionService;
	
	@JmsListener(destination = "${jms.queue}")
	public void receiveMessage(String message) {
		LOGGER.info(message);	
		
        ObjectMapper mapper = new ObjectMapper();
		try {
			
			TransactionDTO transactionDTO = mapper.readValue(message, TransactionDTO.class);
			try {
				transactionService.save(transactionDTO);
			} catch (FinancialTransactionManegementException e) {
				e.printStackTrace();
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
}
