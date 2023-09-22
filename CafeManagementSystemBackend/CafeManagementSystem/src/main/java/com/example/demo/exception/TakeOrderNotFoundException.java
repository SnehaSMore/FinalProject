package com.example.demo.exception;

public class TakeOrderNotFoundException extends RuntimeException {

    public TakeOrderNotFoundException(String message) {
        super(message);
    }
}
