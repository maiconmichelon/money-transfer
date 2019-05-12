package com.michelon.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static spark.utils.StringUtils.isEmpty;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.michelon.dto.request.CreateAccountRequestDto;
import com.michelon.dto.response.CreateAccountResponseDto;
import com.michelon.dto.response.ResponseDto;
import com.michelon.model.Account;
import com.michelon.service.AccountService;

import spark.Request;

public class AccountControllerTest {

	private AccountController accountController;
	private AccountService accountService;

	@Before
	public void setup() {
		accountService = mock(AccountService.class);
		accountController = new AccountController(accountService);
	}
	
	@Test
	public void givenInvalidInitialBalanceWhenHandleThenThrowsAnException() {
		CreateAccountRequestDto createAccount = new CreateAccountRequestDto(null);
		
		try {
			accountController.handle(mock(Request.class), createAccount);
		} catch(IllegalArgumentException ex) {
			assertThat(ex.getMessage(), equalTo("initialBalance must be not null."));
		}
	}
	
	@Test
	public void givenValidRequestWhenHandleThenReturnsSuccess() {
		BigDecimal initialBalance = BigDecimal.ZERO;
		CreateAccountRequestDto createAccount = new CreateAccountRequestDto(initialBalance);
		when(accountService.createNewAccount(Mockito.eq(createAccount))).thenReturn(new Account(initialBalance));
		
		ResponseDto<CreateAccountResponseDto> responseDto = accountController.handle(mock(Request.class), createAccount);
		
		assertThat(responseDto.getStatusCode(), equalTo(201));
		assertThat(responseDto.getData(), notNullValue());
		assertFalse("AccountId should not be null.", isEmpty(responseDto.getData().getId()));
	}
	
}
