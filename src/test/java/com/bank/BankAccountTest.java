package com.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void testDepositSuccess() {
        BankAccount account = new BankAccount("ACC001", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawSuccess() {
        BankAccount account = new BankAccount("ACC002", 200.0);
        boolean result = account.withdraw(50.0);
        assertTrue(result);
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount("ACC003", 50.0);
        boolean result = account.withdraw(100.0);
        assertFalse(result);
        assertEquals(50.0, account.getBalance(), 0.001);
    }

    @Test
    void testConstructorInvalidAccountNumber() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null, 100.0));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("", 100.0));
    }

    @Test
    void testConstructorNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("ACC001", -50.0));
    }
    @Test
    void testDepositInvalidAmount() {
        BankAccount account = new BankAccount("ACC001", 100.0);
        account.deposit(-20.0);
        account.deposit(0.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }
    @Test
    void testWithdrawInvalidAmount() {
        BankAccount account = new BankAccount("ACC002", 200.0);
        boolean result = account.withdraw(-10.0);
        assertFalse(result);
        assertEquals(200.0, account.getBalance(), 0.001);
    }

}