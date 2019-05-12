package com.michelon.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.michelon.dto.response.BalanceResponseDto;
import com.michelon.dto.response.ResponseDto;
import com.michelon.exception.AccountNotFoundException;
import com.michelon.model.Account;
import com.michelon.service.AccountService;

import spark.Request;

public class BalanceControllerTest {

	private static final String VALID_ACCOUNT_ID = "1";
	private static final BigDecimal BALANCE = BigDecimal.valueOf(500);
	
	private static final String INVALID_ACCOUNT_ID = "2";

	private BalanceController balanceController;
	private AccountService accountService;

	@Before
	public void setup() throws Exception {
		accountService = mock(AccountService.class);
		balanceController = new BalanceController(accountService);
		
		when(accountService.getAccount(VALID_ACCOUNT_ID)).thenReturn(new Account(BALANCE));
		when(accountService.getAccount(INVALID_ACCOUNT_ID)).thenThrow(new AccountNotFoundException("Account was not found."));
	}
	
	@Test
	public void givenInvalidAccountWhenHandleThenThrowsAnException() {
		Request request = mock(Request.class);
		when(request.params(BalanceController.PARAM_ACCOUNT_ID)).thenReturn(INVALID_ACCOUNT_ID);
		
		ResponseDto<BalanceResponseDto> responseDto = balanceController.handle(request);
		
		assertThat(responseDto.getError(), notNullValue());
		assertThat(responseDto.getError().getMessage(), equalTo("Account was not found."));
	}
	
	@Test
	public void givenValidRequestWhenHandleThenReturnsSuccess() {
		Request request = mock(Request.class);
		when(request.params(BalanceController.PARAM_ACCOUNT_ID)).thenReturn(VALID_ACCOUNT_ID);
		
		ResponseDto<BalanceResponseDto> responseDto = balanceController.handle(request);
		
		assertThat(responseDto.getStatusCode(), equalTo(200));
		assertThat(responseDto.getData(), notNullValue());
		assertThat(responseDto.getData().getBalance(), equalTo(BALANCE));
	}
	
}
