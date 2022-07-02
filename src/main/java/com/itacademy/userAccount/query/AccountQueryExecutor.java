package com.itacademy.userAccount.query;

import com.itacademy.userAccount.model.Account;
import com.itacademy.userAccount.model.User;

import java.sql.*;
import java.util.ArrayList;

import static com.itacademy.userAccount.constant.SqlQuery.*;
import static java.lang.String.format;


public class AccountQueryExecutor {

    public static Double getAccountBalanceFromDd(int accountId, Connection connection) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_GET_BALANCE);
        statement.setInt(1, accountId);
        double balance = 0.0;
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            balance = resultSet.getDouble("balance");
        }
        resultSet.close();
        statement.close();
        return balance;
    }

    public static ArrayList<String> getAccountsCurrenciesFromDd(User user, Connection connection) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_GET_CURRENCY);
        int userId = user.getUserId();
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        String currency = "";
        ArrayList<String> currencies = new ArrayList<>();
        while (resultSet.next()) {
            currency = resultSet.getString("currency");
            currencies.add(currency);
        }
        resultSet.close();
        statement.close();
        System.out.println("Accounts in the following currencies exist: " + currencies);
        return currencies;
    }

    public static ArrayList<Integer> getAccountsIdsFromDd(User user, Connection connection) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_GET_ACCOUNTS_ID);
        int userId = user.getUserId();
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        int accountId = 0;
        ArrayList<Integer> userAccounts = new ArrayList<>();
        while (resultSet.next()) {
            accountId = resultSet.getInt("accountId");
            userAccounts.add(accountId);
            System.out.println("Account with accountId " + accountId + " exists!");
        }
        resultSet.close();
        statement.close();
        System.out.println(userAccounts);
        return userAccounts;
    }

    public static void addNewAccountForUserToDb(Account account,
                                                User user,
                                                Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format(SQL_INSERT_ACCOUNT,
                account.getAccountId(),
                user.getUserId(),
                account.getBalance(),
                account.getCurrency()));
        statement.close();
    }

    public static void updateAccountBalanceInDB(Account account, double newBalance, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format(SQL_UPDATE_BALANCE,
                newBalance, account.getAccountId()));
        statement.close();
    }
}
