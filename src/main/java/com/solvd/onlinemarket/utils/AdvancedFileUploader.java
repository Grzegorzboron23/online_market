package com.solvd.onlinemarket.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdvancedFileUploader {
    private static final Logger LOGGER = LogManager.getLogger(AdvancedFileUploader.class);

    public static void uploadFile(String fileName) {
        LOGGER.info("Uploading file: {}", fileName);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("File {} uploaded successfully!", fileName);
    }


    public static void performOtherActions() {
        for (int i = 1; i <= 10; i++) {
            LOGGER.info("Performing rest actions {}", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
