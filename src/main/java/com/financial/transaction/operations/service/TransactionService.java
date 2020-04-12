package com.financial.transaction.operations.service;

import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financial.transaction.operations.dto.TransactionDTO;
import com.financial.transaction.operations.dto.business.ClientBusinessDTO;
import com.financial.transaction.operations.exceptions.FinancialTransactionManegementException;
import com.financial.transaction.operations.model.Transaction;
import com.financial.transaction.operations.repository.TransactionRepository;
import com.financial.transaction.operations.rest.integration.BusinessRestIntegration;
import com.financial.transaction.operations.rest.integration.RedisRestIntegration;

@Service
public class TransactionService {

	@Autowired
	private BusinessRestIntegration businessRestIntegration;
	
	@Autowired
	private RedisRestIntegration redisRestIntegration ;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);
	
	public Transaction save(TransactionDTO transactionDTO) throws FinancialTransactionManegementException{

		LOGGER.info("Salvar transação: {}", transactionDTO);
		
		try {
			
			//Realiza a busca do cliente pelo CPF
			ClientBusinessDTO clientBusinessDTO = businessRestIntegration.findByNumberCard(transactionDTO.getNumberCard());
			if(clientBusinessDTO == null) {
				throw new FinancialTransactionManegementException("O Clinte não foi encontrado.");
			}
			
			//Realiza o debito no saldo do clientecpf
			clientBusinessDTO = businessRestIntegration.debitBalance(transactionDTO);
			if(clientBusinessDTO == null) {
				throw new FinancialTransactionManegementException("Não foi possível atualizar o saldo.");
			}					
			
			//Salva a transação
			Transaction transaction = new Transaction();
			transaction.setClientBusiness(clientBusinessDTO);
			transaction.setDateCreate(new Date());
			transaction.setDateOperation(transactionDTO.getDateOperation());// TO DO
			transaction.setHashTransaction(generateHashTransaction(transactionDTO));
			transaction.setValue(transactionDTO.getValue());		
			transactionRepository.save(transaction);			
						
			//atualiar redis
			redisRestIntegration.updateRedis(clientBusinessDTO);
			
			return transaction;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FinancialTransactionManegementException("Ocorreu um erro ao criar a transação.");
		}
	}
	
	private String generateHashTransaction(TransactionDTO transactionDTO) {	
		String hashJoin = transactionDTO.getNumberCard() + transactionDTO.getValue().toString() + String.valueOf(new Date().getTime());
		String hashDone = Base64.getEncoder().encodeToString(hashJoin.getBytes());		
		return hashDone;
	}
	
}
