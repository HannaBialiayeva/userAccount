package com.itacademy.userAccount.service;

import com.itacademy.userAccount.exception.IncorrectAmountOfTransactionException;
import com.itacademy.userAccount.model.Transaction;

import java.util.Scanner;

public class AccountTransaction {

    public static Transaction createPositiveTransaction() throws IncorrectAmountOfTransactionException {

        Transaction transaction = new Transaction();
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        System.out.println("Enter transaction amount: ");
        double amount = scanner.nextDouble();
        do {
            if (amount < 200000) {
                transaction.setTransactionAmount(amount);
            } else {
                throw new IncorrectAmountOfTransactionException("Transaction amount can't be more" +
                        " then 200000 and less then 0");
            }
        }
        while (!valid);
        System.out.println("Success! Amount of transaction is " + transaction.getTransactionAmount());
        return transaction;
    }

    public static Transaction createNegativeTransaction () {

        Transaction transaction = new Transaction();
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        do {
            try {
                System.out.println("Enter transaction amount: ");
                transaction.setTransactionAmount(scanner.nextDouble() * -1);
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
            }
        }
        while (!valid);
        System.out.println("Amount of transaction is " + transaction.getTransactionAmount());
        return transaction;
    }

    public static Transaction startTransaction(int operation) throws IncorrectAmountOfTransactionException {
        System.out.println("Transaction is started!");
        Transaction transaction = null;
        switch (operation) {
            case (1) -> transaction = createPositiveTransaction();
            case (2) -> transaction = createNegativeTransaction();
            default -> System.out.println("Invalid operation! Try again");
        }
        return transaction;
    }

}
