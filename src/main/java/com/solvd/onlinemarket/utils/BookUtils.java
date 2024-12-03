package com.solvd.onlinemarket.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class BookUtils {

    public static String readFile(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath), Charset.defaultCharset());
    }

    public static void saveToFile(String filePath, String content) throws IOException {
        FileUtils.writeStringToFile(new File(filePath), content, Charset.defaultCharset());
    }
}
