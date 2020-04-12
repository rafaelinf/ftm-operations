package com.financial.transaction.operations.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class OperationsExceptionHandler {

	private Logger log = LoggerFactory.getLogger(OperationsExceptionHandler.class);

	@ExceptionHandler(FinancialTransactionManegementException.class)
	public void handlePostNotFound(HttpServletRequest request, Exception ex) {
		log.error("{} : {}", ex.getMessage(), request.getRequestURI());
	}
}