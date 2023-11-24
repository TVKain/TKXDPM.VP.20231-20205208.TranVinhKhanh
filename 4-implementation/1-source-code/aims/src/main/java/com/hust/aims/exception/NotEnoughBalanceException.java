package com.hust.aims.exception;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
