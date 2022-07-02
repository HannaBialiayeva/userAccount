package com.itacademy.userAccount.query;
import com.itacademy.userAccount.model.User;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itacademy.userAccount.constant.SqlQuery.*;
import static java.lang.String.format;

public class UserQueryExecutor {

    public static void printUserInfo(User user, Connection connection) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_SELECT_USER_FULL_INFO);
        int userId = user.getUserId();
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println('\n' + "UserId: " + resultSet.getInt("userId"));
            System.out.println("UserName: " + resultSet.getString("name"));
            System.out.println("AccountId: " + resultSet.getInt("accountId"));
            System.out.println("Balance: " + resultSet.getDouble("balance"));
            System.out.println("Currency: " + resultSet.getString("currency"));
        }
        resultSet.close();
        statement.close();
    }

    public static String getUserNameFromDb(User user, Connection connection) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_FIND_USER_NAME);
        int userId = user.getUserId();
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        String name = "";
        while (resultSet.next()) {
            name = resultSet.getString("name");

        }
        resultSet.close();
        statement.close();
        System.out.println(name);

        return name;
    }

    public static void addNewUserToDb(User user, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(format(SQL_INSERT_NEW_USER, user.getUserId(),
                user.getUserName(), user.getUserAddress()));
        statement.close();
    }

}
