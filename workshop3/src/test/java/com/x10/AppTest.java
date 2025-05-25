package com.x10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class AppTest {

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount("Test Account", 100.0);
        account.deposit(50.0);
        assertEquals(BigDecimal.valueOf(150.0), account.getBalance());
    }

    @Test
    void testWithdrawSuccess() {
        BankAccount account = new BankAccount("Test Account", 200.0);
        boolean result = account.withdraw(50.0);
        assertTrue(result);
        assertEquals(BigDecimal.valueOf(150.0), account.getBalance());
    }

    @Test
    void testWithdrawFail() {
        BankAccount account = new BankAccount("Test Account", 30.0);
        boolean result = account.withdraw(50.0);
        assertFalse(result);
        assertEquals(BigDecimal.valueOf(30.0), account.getBalance());
    }

    @Test
    void testGetBalance() {
        BankAccount account = new BankAccount("Test Account", 500.0);
        assertEquals(BigDecimal.valueOf(500.0), account.getBalance());
    }
    
    @Test
    void testBankAccountName() {
        BankAccount account = new BankAccount("My Savings", 1000.0);
        assertEquals("My Savings", account.getBankAccountName());
    }

}
