package com.bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BankAccount {

        private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);

    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            logger.error("Failed to create account: Invalid account number provided.");
            throw new IllegalArgumentException("Account number cannot be null or empty.");
        }
        if (initialBalance < 0) {
            logger.error("Failed to create account {}: Initial balance cannot be negative ({})",
                    accountNumber, initialBalance);
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        logger.info("Account successfully created. AccountNumber: {}, InitialBalance: {}",
                accountNumber, initialBalance);
    }

    public synchronized void deposit(double amount) {
        logger.debug("Attempting deposit of {} into account {}", amount, accountNumber);
        if (amount <= 0) {
            logger.warn("Deposit failed for account {}: Amount must be positive (Attempted: {})",
                    accountNumber, amount);
            return;
        }

        this.balance += amount;
        logger.info("Deposit successful. Account: {}, Amount: {}, NewBalance: {}",
                accountNumber, amount, balance);
    }

    public synchronized boolean withdraw(double amount) {
        logger.debug("Attempting withdrawal of {} from account {}", amount, accountNumber);
        if (amount <= 0) {
            logger.warn("Withdrawal failed for account {}: Invalid amount ({})", accountNumber, amount);
            return false;
        }

        if (amount > balance) {
            logger.warn("Withdrawal rejected for account {}: Insufficient funds (Requested: {}, Available: {})",
                    accountNumber, amount, balance);
            return false;
        }

        this.balance -= amount;
        logger.info("Withdrawal successful. Account: {}, Amount: {}, RemainingBalance: {}",
                accountNumber, amount, balance);
        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getStatementFilePath(String baseDir) {
        Path path = Paths.get(baseDir, accountNumber + "_statement.txt");
        return path.toString();
    }

    public synchronized double getBalance() {
        return balance;
    }
}
