package com.michelon.application;

import com.michelon.repository.AccountRepository;
import com.michelon.service.AccountService;
import com.michelon.service.TransactionService;

public class Configuration {

    private static AccountRepository accountRepository = new AccountRepository();
    private static AccountService accountService = new AccountService(accountRepository);
    private static TransactionService transactionService = new TransactionService(accountRepository);

    public static AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    public static TransactionService getTransactionService() {
        return transactionService;
    }
}
