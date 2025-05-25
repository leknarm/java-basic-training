package com.x10;

import java.math.BigDecimal;

public class BankAccount {
    private final String bankAccountName;
    private BigDecimal balance;

    public BankAccount(String bankAccountName, double initialBalance) {
        this.bankAccountName = bankAccountName;
        this.balance = BigDecimal.valueOf(initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance.add(BigDecimal.valueOf(amount));
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance.compareTo(BigDecimal.valueOf(amount)) >= 0) {
            balance = balance.subtract(BigDecimal.valueOf(amount));
            return true;
        }
        return false;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }
}
