package com.solvd.onlinemarket.service;


import com.solvd.onlinemarket.utils.BookUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BookService {

    private static final Logger LOGGER = LogManager.getLogger(BookService.class);

    public static int countWordsInBook(String inputFilePath, String outputFilePath) {
        try {
            String content = BookUtils.readFile(inputFilePath);

            String[] words = StringUtils.split(content.toLowerCase(), " ");
            Set<String> uniqueWords = new HashSet<>(List.of(words));
            int uniqueWordCount = uniqueWords.size();

            BookUtils.saveToFile(outputFilePath, "Number of unique words: " + uniqueWordCount);
            return uniqueWordCount;
        } catch (IOException e) {
            LOGGER.error("Error processing the book file ", e);
            return -1;
        }
    }
}
