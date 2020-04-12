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
public class RedisRestIntegration {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisRestIntegration.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${url-base.redis}")
	private String urlBase;

	public ClientBusinessDTO updateRedis(ClientBusinessDTO clientBusinessDTO)
			throws FinancialTransactionManegementException {
		try {

			LOGGER.info("Atualizar saldo - cpf: {}", clientBusinessDTO.getCpf());

			String url = urlBase + "clients/save";

			ClientBusinessDTO clientBusinessDTOresult = restTemplate.postForObject(url, clientBusinessDTO, ClientBusinessDTO.class);
			return clientBusinessDTOresult;

		} catch (HttpClientErrorException e) {
			LOGGER.error("Erro ao atualizar o saldo cpf: {} ,Response {}", clientBusinessDTO.getCpf(),
					e.getResponseBodyAsString(), e);
			throw new FinancialTransactionManegementException(
					"Ocorreu um erro na chamada do endpoint do projeto business");
		}
	}
}
