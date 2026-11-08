package com.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("=== Khoi chay ung dung BankSystem ===");

        try {
            BankAccount account = new BankAccount("ACC-MAIN-001", 500.0);
            logger.info("Tao tai khoan thanh cong: {}", account.getAccountNumber());

            account.deposit(200.0);
            logger.info("So du hien tai sau khi nap: {}", account.getBalance());

            account.withdraw(150.0);
            logger.info("So du hien tai sau khi rut: {}", account.getBalance());

            logger.info("=== Ung dung BankSystem hoan thanh xu ly thành cong ===");
        } catch (Exception e) {
            logger.error("Da xay ra loi trong qua trinh thuc thi: ", e);
        }
    }
}
