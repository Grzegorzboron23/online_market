package com.solvd.onlinemarket.utils;

public class AdvancedFileUploader {

    public static void uploadFile(String fileName) {
        System.out.println("Uploading file: " + fileName);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("File " + fileName + " uploaded successfully!");
    }


    public static void performOtherActions() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Performing rest actions " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
