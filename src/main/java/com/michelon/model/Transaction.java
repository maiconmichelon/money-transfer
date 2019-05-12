package com.michelon.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction {

    private final LocalDateTime time;
    private final String id;
    private final Account source;
    private final Account target;
    private final BigDecimal value;

    public Transaction(Account source, Account target, BigDecimal value) {
        this.id = UUID.randomUUID().toString();
        this.source = source;
        this.target = target;
        this.value = value;
        this.time = LocalDateTime.now();
    }

    public Account getSource() {
        return source;
    }

    public Account getTarget() {
        return target;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
