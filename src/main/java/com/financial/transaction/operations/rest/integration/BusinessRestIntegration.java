package com.financial.transaction.operations.rest.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.financial.transaction.operations.dto.TransactionDTO;
import com.financial.transaction.operations.dto.business.ClientBusinessDTO;
import com.financial.transaction.operations.exceptions.FinancialTransactionManegementException;

@Component
public class BusinessRestIntegration {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessRestIntegration.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${url-base.business}")
	private String urlBase;

	public ClientBusinessDTO findClientByCpf(String cpf) throws FinancialTransactionManegementException {
		try {
			
			LOGGER.debug("Buscar cliente por cpf: {}", cpf);

			String url = urlBase + "clients/cpf/" + cpf;
			ClientBusinessDTO clientBusinessDTO = restTemplate.getForObject(url, ClientBusinessDTO.class);
			LOGGER.debug("Buscar cliente retorno: {}", clientBusinessDTO);

			return clientBusinessDTO;

		} catch (HttpClientErrorException e) {
			LOGGER.error("Erro ao buscar cpf: {} ,Response {}", cpf, e.getResponseBodyAsString(), e);
			throw new FinancialTransactionManegementException("Ocorreu um erro na chamada do endpoint do projeto business");
		}
	}
	
	public ClientBusinessDTO findByNumberCard(String numberCard) throws FinancialTransactionManegementException {
		try {
			
			LOGGER.debug("Buscar cliente por numberCard: {}", numberCard);

			String url = urlBase + "clients/numberCard/" + numberCard;
			ClientBusinessDTO clientBusinessDTO = restTemplate.getForObject(url, ClientBusinessDTO.class);
			LOGGER.debug("Buscar cliente retorno: {}", clientBusinessDTO);

			return clientBusinessDTO;

		} catch (HttpClientErrorException e) {
			LOGGER.error("Erro ao buscar numberCard: {} ,Response {}", numberCard, e.getResponseBodyAsString(), e);
			throw new FinancialTransactionManegementException("Ocorreu um erro na chamada do endpoint do projeto business");
		}
	}	
	
	public ClientBusinessDTO debitBalance(TransactionDTO transactionDTO) throws FinancialTransactionManegementException {
		try {

			LOGGER.info("Atualizar saldo - cpf: {}; valor: {}", transactionDTO.getNumberCard(), transactionDTO.getValue());

			String url = urlBase + "balance/debitBalance";
			
			ClientBusinessDTO clientBusinessDTO = restTemplate.postForObject(url, transactionDTO, ClientBusinessDTO.class);	
		    return clientBusinessDTO;

		} catch (HttpClientErrorException e) {
			LOGGER.error("Erro ao atualizar o saldo numberCard: {} ,Response {}", transactionDTO.getNumberCard(), e.getResponseBodyAsString(), e);
			throw new FinancialTransactionManegementException("Ocorreu um erro na chamada do endpoint do projeto business");
		}
	}	

}
