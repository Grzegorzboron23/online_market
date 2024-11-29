package com.solvd.onlinemarket.utils;

import com.solvd.onlinemarket.exception.FileProcessingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileReadWithResourcesUtil implements AutoCloseable {

    public static List<String> getResourcesFromFile(String filePath) throws FileProcessingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().toList();
        } catch (IOException e) {
            throw new FileProcessingException("Error processing file: " + filePath, e);
        }
    }


    @Override
    public void close() throws Exception {
        System.out.println("File is closing");
    }
}
