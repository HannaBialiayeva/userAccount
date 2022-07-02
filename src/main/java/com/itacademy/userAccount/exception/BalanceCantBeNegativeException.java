package com.itacademy.userAccount.exception;

public class BalanceCantBeNegativeException extends Exception {

    public BalanceCantBeNegativeException(String message) {
        super(message);
    }
}
