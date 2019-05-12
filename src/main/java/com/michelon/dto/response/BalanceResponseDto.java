package com.michelon.dto.response;

import java.math.BigDecimal;

public class BalanceResponseDto {

    private final BigDecimal balance;

    public BalanceResponseDto(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
