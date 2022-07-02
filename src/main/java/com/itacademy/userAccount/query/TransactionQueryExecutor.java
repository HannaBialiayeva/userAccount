package com.itacademy.userAccount.query;

import com.itacademy.userAccount.model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.itacademy.userAccount.constant.SqlQuery.INSERT_TRANSACTION;
import static java.lang.String.format;

public class TransactionQueryExecutor {

    public static void saveTransactionToDb(Transaction transaction,
                                           int accountId,
                                           Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format(INSERT_TRANSACTION,
                accountId, transaction.getTransactionAmount()));
        statement.close();
    }
}
