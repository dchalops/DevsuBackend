package com.sofka.accountsmovementsservice.infrastructure.exc;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

