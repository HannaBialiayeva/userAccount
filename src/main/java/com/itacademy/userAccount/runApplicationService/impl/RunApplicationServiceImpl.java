package com.itacademy.userAccount.runApplicationService.impl;

import com.itacademy.userAccount.exception.AccountWithSuchCurrencyAlreadyExistsException;
import com.itacademy.userAccount.exception.BalanceCantBeNegativeException;
import com.itacademy.userAccount.exception.IncorrectAmountOfTransactionException;
import com.itacademy.userAccount.exception.InvalidDataGettingException;
import com.itacademy.userAccount.model.Account;
import com.itacademy.userAccount.model.Transaction;
import com.itacademy.userAccount.model.User;
import com.itacademy.userAccount.runApplicationService.RunApplicationService;

import java.sql.*;
import java.util.ArrayList;

import static com.itacademy.userAccount.constant.DbConnection.DATABASE_URL;
import static com.itacademy.userAccount.constant.DbConnection.JDBC_DRIVER_PATH;
import static com.itacademy.userAccount.query.AccountQueryExecutor.*;
import static com.itacademy.userAccount.query.TransactionQueryExecutor.saveTransactionToDb;
import static com.itacademy.userAccount.query.UserQueryExecutor.*;
import static com.itacademy.userAccount.service.AccountTransaction.*;
import static com.itacademy.userAccount.service.UserAccountCreation.*;
import static com.itacademy.userAccount.service.UserRegistration.createUser;
import static com.itacademy.userAccount.service.UserRegistration.enterUserId;
import static com.itacademy.userAccount.util.OptionSelector.selectOption;

public class RunApplicationServiceImpl implements RunApplicationService {

    @Override
    public void runApplication() throws SQLException, InvalidDataGettingException, AccountWithSuchCurrencyAlreadyExistsException, IncorrectAmountOfTransactionException, BalanceCantBeNegativeException {

        if (isDriverExists()) {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Are you a new user?");
            User user = new User();
            Account account = new Account();
            System.out.println("If your answer is YES, enter 1\n" +
                    "If your answer is  NO, enter 2");
            int option = selectOption();
            int userId = 0;
            switch (option) {
                case (1):
                    user = createUser();
                    addNewUserToDb(user, connection);
                    System.out.println("Do you want to create account?");
                    System.out.println("If your answer is YES, enter 1\n" +
                            "If your answer is  NO, enter 2");
                    break;
                case (2):
                    userId = enterUserId();
                    user.setUserId(userId);
                    System.out.println("User with name " + getUserNameFromDb(user, connection) +
                            " was found");
                    System.out.println("Do you want to create an account or use existing? " +
                            "Please, select an option: if YES - 1, if NO - 2");
                    break;
                default:
                    throw new InvalidDataGettingException("Invalid option was selected!");
            }
            userId = user.getUserId();
            int accountId = 0;
            option = selectOption();
            switch (option) {
                case (2):
                    ArrayList<Integer> accountIds = getAccountsIdsFromDd(user, connection);
                    accountId = accountIds.get(0);
                    break;
                case (1):
                    account = createUserAccount(userId);
                    String currency = account.getCurrency();
                    ArrayList<String> currencies = getAccountsCurrenciesFromDd(user, connection);
                    if (currencies.contains(currency.toUpperCase())) {
                        throw new AccountWithSuchCurrencyAlreadyExistsException("Can't create account" +
                                " in selected currency! Try again!");
                    } else {
                        addNewAccountForUserToDb(account, user, connection);
                    }
                    break;
                default:
                    throw new InvalidDataGettingException("Invalid option was selected!");
            }
            accountId = account.getAccountId();
            System.out.println("Select transaction type");
            System.out.println("If money IN, enter 1\n" +
                    "If money OUT, enter 2");
            int typeOfTransaction = selectOption();
            Transaction transaction = startTransaction(typeOfTransaction);
            saveTransactionToDb(transaction, accountId, connection);
            double amount = transaction.getTransactionAmount();
            double currentBalance = getAccountBalanceFromDd(accountId, connection);
            double increasedBalance = updateBalance(currentBalance, amount);
            if (increasedBalance < 0) {
                throw new BalanceCantBeNegativeException("Balance can't be negative");
            }
            account.setBalance(increasedBalance);
            updateAccountBalanceInDB(account, increasedBalance, connection);
            getAccountBalanceFromDd(accountId, connection);
            System.out.println("Current balance: " + increasedBalance);
            System.out.println("User's accounts list: ");
            printUserInfo(user, connection);
        }
    }

    private static boolean isDriverExists() {
        try {
            Class.forName(JDBC_DRIVER_PATH);
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC Driver not found");
            return false;
        }
    }

}
