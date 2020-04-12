package com.financial.transaction.operations.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.financial.transaction.operations.dto.business.ClientBusinessDTO;

@Document(collection = "transaction")
public class Transaction {

	@Id
	public String id;
	public ClientBusinessDTO clientBusiness;
	public BigDecimal value;
	public Date dateCreate;
	public Date dateOperation;
	public String hashTransaction;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ClientBusinessDTO getClientBusiness() {
		return clientBusiness;
	}

	public void setClientBusiness(ClientBusinessDTO clientBusiness) {
		this.clientBusiness = clientBusiness;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public String getHashTransaction() {
		return hashTransaction;
	}

	public void setHashTransaction(String hashTransaction) {
		this.hashTransaction = hashTransaction;
	}
	
}
