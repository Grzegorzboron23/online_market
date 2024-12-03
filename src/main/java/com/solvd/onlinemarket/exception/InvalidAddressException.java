package com.solvd.onlinemarket.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidAddressException extends Exception {

    private static final Logger logger = LogManager.getLogger(InvalidAddressException.class);

    public InvalidAddressException(String message) {
        super(message);
        logger.error("InvalidAddressException occurred: {}", message);
    }
}