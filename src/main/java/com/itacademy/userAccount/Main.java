package com.itacademy.userAccount;

import com.itacademy.userAccount.exception.AccountWithSuchCurrencyAlreadyExistsException;
import com.itacademy.userAccount.exception.BalanceCantBeNegativeException;
import com.itacademy.userAccount.exception.IncorrectAmountOfTransactionException;
import com.itacademy.userAccount.exception.InvalidDataGettingException;
import com.itacademy.userAccount.runApplicationService.RunApplicationService;
import com.itacademy.userAccount.runApplicationService.impl.RunApplicationServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, AccountWithSuchCurrencyAlreadyExistsException, InvalidDataGettingException, IncorrectAmountOfTransactionException, BalanceCantBeNegativeException {

        RunApplicationService runApplicationService = new RunApplicationServiceImpl();
        runApplicationService.runApplication();

    }
}
