package com.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccount {

    private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);

    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            logger.error("Failed to create account: Account number is null or empty");
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (initialBalance < 0) {
            logger.error("Failed to create account {}: Initial balance cannot be negative ({})",
                    accountNumber, initialBalance);
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        logger.info("Account successfully created. AccountNumber: {}, InitialBalance: {}",
                accountNumber, initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            logger.error("Deposit failed for account {}: Invalid amount ({})", accountNumber, amount);
            return;
        }
        this.balance += amount;
        logger.info("Deposit successful. Account: {}, Amount: {}, NewBalance: {}",
                accountNumber, amount, balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            logger.error("Withdrawal failed for account {}: Invalid amount ({})", accountNumber, amount);
            return false;
        }
        if (amount > balance) {
            logger.error("Withdrawal failed for account {}: Insufficient balance. Requested: {}, Current: {}",
                    accountNumber, amount, balance);
            return false;
        }
        this.balance -= amount;
        logger.info("Withdrawal successful. Account: {}, Amount: {}, RemainingBalance: {}",
                accountNumber, amount, balance);
        return true;
    }
}