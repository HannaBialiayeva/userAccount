package com.itacademy.userAccount.constant;

public class SqlQuery {

    public static final String SQL_GET_BALANCE = "SELECT balance FROM Accounts WHERE accountId = ?";
    public static final String SQL_GET_CURRENCY = "SELECT currency FROM Accounts WHERE userId = ?";
    public static final String SQL_GET_ACCOUNTS_ID = "SELECT accountId FROM Accounts WHERE userId = ?";
    public static final String SQL_INSERT_ACCOUNT = "INSERT INTO Accounts (accountId, userId, balance, currency) " +
            "VALUES(%d, %d, '%s','%s')";
    public static final String SQL_UPDATE_BALANCE = "UPDATE Accounts SET balance = '%s' " +
            "WHERE accountId = %d;";
    public static final String INSERT_TRANSACTION = "INSERT INTO " +
            "Transactions (accountId, amount) " +
            "VALUES(%d, '%s')";
    public static final String SQL_FIND_USER_NAME = "SELECT name FROM Users WHERE userId = ?";
    public static final String SQL_INSERT_NEW_USER = "INSERT INTO Users (userId, name, address) " +
            "VALUES(%d,'%s','%s')";
    public static final String SQL_SELECT_USER_FULL_INFO = "SELECT \n" +
            "u.name, \n" +
            "u.userId, \n" +
            "a.accountId, \n" +
            "a.balance, \n" +
            "a.currency \n" +
            "FROM Users as u\n" +
            "JOIN  Accounts as a\n" +
            "ON a.userId = u.userId\n" +
            "WHERE u.userId = ? ";

}
