package com.solvd.onlinemarket.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileProcessingException extends Exception {

    private static final Logger logger = LogManager.getLogger(FileProcessingException.class);

    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
        logger.error("FileProcessingException occurred: {}. Cause: {}", message, cause.getMessage(), cause);
    }

    public FileProcessingException(String message) {
        super(message);
        logger.error("FileProcessingException occurred: {}.", message);
    }
}
