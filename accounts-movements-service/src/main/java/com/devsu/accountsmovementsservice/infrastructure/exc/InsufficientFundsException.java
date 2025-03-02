package com.devsu.accountsmovementsservice.infrastructure.exc;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

