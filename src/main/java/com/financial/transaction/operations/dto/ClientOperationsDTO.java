package com.financial.transaction.operations.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClientOperationsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String cpf;
	private String numberCard;
	private String passwordCard;
	private BigDecimal balance;
	private String status;
	private Integer score;
	private Integer version;
	
}

