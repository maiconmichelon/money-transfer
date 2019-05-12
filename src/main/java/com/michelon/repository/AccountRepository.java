package com.michelon.repository;

import java.util.concurrent.ConcurrentHashMap;

import com.michelon.exception.AccountNotFoundException;
import com.michelon.model.Account;

public class AccountRepository {

    private final ConcurrentHashMap<String, Account> accounts;

    public AccountRepository() {
        this.accounts = new ConcurrentHashMap<String, Account>();
    }

    public void save(Account account) {
        accounts.put(account.getId(), account);
    }

    public Account getAccount(String id) throws AccountNotFoundException {
        Account account = accounts.get(id);
        if (account == null) {
        	throw new AccountNotFoundException(String.format("Account %s was not found.", id));
        }
        
		return account;
    }

}
