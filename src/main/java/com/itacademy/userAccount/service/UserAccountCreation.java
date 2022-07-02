package com.itacademy.userAccount.service;

import com.itacademy.userAccount.model.Account;
import com.itacademy.userAccount.model.User;

import java.util.Scanner;

import static com.itacademy.userAccount.util.RandomNumberGenerator.generateRandomNumber;

public class UserAccountCreation {

    public static Account createUserAccount(int userId) {
        Account account = new Account();
        int accountId = generateRandomNumber();
        account.setAccountId(accountId);
        account.setBalance(0.0);
        account.setCurrency(selectCurrency());
        System.out.println("Success! New account " + account.getAccountId() + " in " +
                account.getCurrency() +
                " was created!");
        return account;
    }

    public static String selectCurrency() {
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        String currency = "";
        do {
            System.out.println("Select currency: EURO, USD, RUB and etc.");
            try {
                if (scanner.hasNextLine()) {
                    currency = scanner.nextLine().toUpperCase();
                }
            } catch (Exception e) {
                System.out.println("Invalid data, Try again");
            }
        } while (!valid);
        System.out.println(currency + " was selected as account currency!");
        return currency;
    }

    public static double updateBalance(double currentBalance, double amount) {

        return currentBalance + amount;
    }

}





