package com.solvd.exception;

public class InvalidSalaryException extends RuntimeException {

    public InvalidSalaryException(String message) {
        super(message);
    }
}