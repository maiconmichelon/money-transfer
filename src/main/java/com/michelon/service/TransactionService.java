package com.michelon.service;

import com.michelon.dto.request.CreateTransactionRequestDto;
import com.michelon.exception.AccountNotFoundException;
import com.michelon.model.Account;
import com.michelon.model.Transaction;
import com.michelon.repository.AccountRepository;

public class TransactionService {

    private final AccountRepository accountRepository;

    public TransactionService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transfer(CreateTransactionRequestDto createTransactionRequestDto) throws AccountNotFoundException {
        Account sourceAccount = accountRepository.getAccount(createTransactionRequestDto.getSourceAccountId());
        Account targetAccount = accountRepository.getAccount(createTransactionRequestDto.getTargetAccountId());

        Transaction transaction = new Transaction(sourceAccount, targetAccount, createTransactionRequestDto.getValue());
        sourceAccount.execute(transaction);
    }

}
