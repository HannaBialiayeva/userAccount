package com.itacademy.userAccount.runApplicationService;

import com.itacademy.userAccount.exception.AccountWithSuchCurrencyAlreadyExistsException;
import com.itacademy.userAccount.exception.BalanceCantBeNegativeException;
import com.itacademy.userAccount.exception.IncorrectAmountOfTransactionException;
import com.itacademy.userAccount.exception.InvalidDataGettingException;

import java.sql.SQLException;

public interface RunApplicationService {

   void runApplication() throws SQLException,
           InvalidDataGettingException,
           AccountWithSuchCurrencyAlreadyExistsException,
           IncorrectAmountOfTransactionException,
           BalanceCantBeNegativeException;
}
