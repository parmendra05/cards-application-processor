package com.banking.cards.application.handler.exception;

public class ApplicationAlreadyProcessedException extends RuntimeException {
    public ApplicationAlreadyProcessedException(String message) {
        super(message);
    }
}
