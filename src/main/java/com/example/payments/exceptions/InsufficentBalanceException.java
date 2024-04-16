package com.example.payments.exceptions;

public class InsufficentBalanceException extends RuntimeException{
    public InsufficentBalanceException() {
    }

    public InsufficentBalanceException(String message) {
        super(message);
    }

    public InsufficentBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficentBalanceException(Throwable cause) {
        super(cause);
    }

    public InsufficentBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
