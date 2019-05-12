package com.michelon.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.michelon.dto.request.CreateTransactionRequestDto;
import com.michelon.dto.response.ResponseDto;
import com.michelon.exception.AccountNotFoundException;
import com.michelon.service.TransactionService;

import spark.Request;

public class TransactionControllerTest {

	private TransactionController transactionController;
	private TransactionService transactionService;
	
	@Before
	public void setup() throws Exception {
		transactionService = Mockito.mock(TransactionService.class);
		transactionController = new TransactionController(transactionService);
	}
	
	@Test
	public void givenInvalidTransactionWhenHandleThenReturnBadRequest() throws Exception {
		doThrow(new AccountNotFoundException("Account was not found")).when(transactionService).transfer(Mockito.any());
		
		ResponseDto<?> dto = transactionController.handle(mock(Request.class), buildRequestDto());
		
		Assert.assertThat(dto.getStatusCode(), equalTo(400));
		Assert.assertThat(dto.getError().getMessage(), equalTo("Account was not found"));
	}

	@Test
	public void givenValidTransactionWhenHandleThenReturnsSuccess() throws Exception {
		ResponseDto<?> dto = transactionController.handle(mock(Request.class), buildRequestDto());
		
		Assert.assertThat(dto.getStatusCode(), equalTo(200));
	}
	
	private CreateTransactionRequestDto buildRequestDto() {
		return new CreateTransactionRequestDto("1", "2", BigDecimal.valueOf(100));
	}
	
}
