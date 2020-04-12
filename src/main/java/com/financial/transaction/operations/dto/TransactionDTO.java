package com.financial.transaction.operations.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public String cpf;
	public String numberCard;
	public BigDecimal value;
	public String password;
	public Date dateOperation;

	public TransactionDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

}
