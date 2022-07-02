package com.itacademy.userAccount.exception;

public class AccountWithSuchCurrencyAlreadyExistsException extends Exception {

    public AccountWithSuchCurrencyAlreadyExistsException(String message) {
        super(message);
    }
}
