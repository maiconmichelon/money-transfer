package com.michelon.dto.request;

import java.math.BigDecimal;

public class CreateAccountRequestDto {

	private final BigDecimal initialBalance;

	public CreateAccountRequestDto(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
	
}
