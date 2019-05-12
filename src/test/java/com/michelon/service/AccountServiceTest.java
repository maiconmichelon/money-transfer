package com.michelon.service;

import java.math.BigDecimal;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.michelon.model.Account;
import com.michelon.repository.AccountRepository;

public class AccountServiceTest {

	private static final BigDecimal BALANCE = BigDecimal.valueOf(5000);
	private static final String VALID_ACCOUNT_ID = "1";
	private AccountService accountService;

	@Before
	public void setup() throws Exception {
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		Mockito.when(accountRepository.getAccount(VALID_ACCOUNT_ID)).thenReturn(new Account(BALANCE));
		accountService = new AccountService(accountRepository);
	}
	
	@Test
	public void givenAnInvalidAccountIdWhenGetBalanceThenThrowsException() throws Exception {
		try {
			accountService.getBalance("2");
		} catch(IllegalArgumentException ex) {
			Assert.assertThat(ex.getMessage(), CoreMatchers.equalTo("Account was not found."));
		}
	}
	
	@Test
	public void givenValidAccountWhenGetBalanceThenReturnTheBalance() throws Exception {
		BigDecimal balance = accountService.getBalance("1");
		
		Assert.assertThat(balance, CoreMatchers.equalTo(BALANCE));
	}
    
}
