package utils;

import exception.FileProcessingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileReadUtil {

    public static List<String> getResourcesFromFile(String filePath) throws FileProcessingException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            return reader.lines().toList();
        } catch (IOException e) {
            throw new FileProcessingException(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the reader: " + e.getMessage());
                }
            }
        }
    }
}
