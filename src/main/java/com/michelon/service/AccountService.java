package com.michelon.service;

import java.math.BigDecimal;

import com.michelon.dto.request.CreateAccountRequestDto;
import com.michelon.exception.AccountNotFoundException;
import com.michelon.model.Account;
import com.michelon.repository.AccountRepository;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(String accountId) throws AccountNotFoundException {
        return accountRepository.getAccount(accountId);
    }

    public BigDecimal getBalance(String accountId) throws AccountNotFoundException {
    	Account account = getAccount(accountId);
    	if (account == null) {
    		throw new IllegalArgumentException("Account was not found.");
    	}
    	
        return account.getBalance();
    }

	public Account createNewAccount(CreateAccountRequestDto newAccountDto) {
		Account account = new Account(newAccountDto.getInitialBalance());
		accountRepository.save(account);
		return account;
	}
    
}
