package com.michelon.model;

import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

	@Test
	public void givenSeveralTransactionsWhenItOccursAtTheSameTimeTheBalanceShouldBeCorrect() throws InterruptedException {
		Account source = new Account(BigDecimal.valueOf(620));
		Account target = new Account(BigDecimal.valueOf(250));
		
		ExecutorService executorService = Executors.newFixedThreadPool(30);
		List<Callable<Object>> callables = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			callables.add(Executors.callable(() -> {
				Transaction transaction = new Transaction(source, target, BigDecimal.valueOf(5));
				source.execute(transaction);
			})); 
		}
		executorService.invokeAll(callables);
		
		Assert.assertThat(source.getBalance(), equalTo(BigDecimal.valueOf(120)));
		Assert.assertThat(target.getBalance(), equalTo(BigDecimal.valueOf(750)));
	}
	
	@Test
	public void givenTransactionSourceAccountInvalidWhenExecuteTransactionThenThrowsException() {
		Account source = new Account(BigDecimal.valueOf(620));
		Account target = new Account(BigDecimal.valueOf(250));
		
		try {
			source.execute(new Transaction(target, source, BigDecimal.valueOf(10)));
		} catch(IllegalArgumentException e) {
			Assert.assertThat(e.getMessage(), equalTo("The transaction is not related to this account."));
		}
	}
	
	@Test
	public void givenValidTransactionWhenThereArentEnoughFundsThenThrowsException() {
		Account source = new Account(BigDecimal.valueOf(620));
		Account target = new Account(BigDecimal.valueOf(250));
		
		try {
			source.execute(new Transaction(source, target, BigDecimal.valueOf(700)));
		} catch(IllegalArgumentException e) {
			Assert.assertThat(e.getMessage(), equalTo("Insufficient funds."));
		}
	}
	
	@Test
	public void givenValidTransactionWhenThereAreEnoughFundsThenUpdateBalance() {
		Account source = new Account(BigDecimal.valueOf(620));
		Account target = new Account(BigDecimal.valueOf(250));
		
		source.execute(new Transaction(source, target, BigDecimal.valueOf(120)));
		
		Assert.assertThat(source.getBalance(), CoreMatchers.equalTo(BigDecimal.valueOf(500)));
		Assert.assertThat(target.getBalance(), CoreMatchers.equalTo(BigDecimal.valueOf(370)));
	}
	
}
