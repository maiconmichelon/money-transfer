package com.michelon.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {

    private final String id;
    private BigDecimal balance;

    public Account() {
    	this(BigDecimal.ZERO);
    }

    public Account(BigDecimal balance) {
        this.id = UUID.randomUUID().toString();
        this.balance = balance;
    }
    
    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private void addCredit(BigDecimal value) {
    	balance = balance.add(value);
    }
    
    private void addDebit(BigDecimal value) {
    	balance = balance.subtract(value);
    }
    
    public synchronized void execute(Transaction transaction) {
		if (!equals(transaction.getSource())) {
            throw new IllegalArgumentException("The transaction is not related to this account.");
        }

        BigDecimal value = transaction.getValue();
        if (getBalance().compareTo(value) < 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        addDebit(value);
        transaction.getTarget().addCredit(value);
    }
    
}
