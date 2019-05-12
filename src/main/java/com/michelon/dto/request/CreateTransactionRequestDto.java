package com.michelon.dto.request;

import java.math.BigDecimal;

public class CreateTransactionRequestDto {

    private final String sourceAccountId;
    private final String targetAccountId;
    private final BigDecimal value;

    public CreateTransactionRequestDto(String sourceAccountId, String targetAccountId, BigDecimal value) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.value = value;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public BigDecimal getValue() {
        return value;
    }
}
