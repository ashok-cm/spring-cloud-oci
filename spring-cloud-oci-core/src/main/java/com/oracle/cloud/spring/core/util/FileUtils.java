package com.oracle.cloud.spring.core.util;

import java.io.File;
import java.io.FileWriter;

public class FileUtils {

    public static void createFile(String filePath, String fileContent) throws Exception {
        File f = new File(filePath);
        if(!f.exists() || f.isDirectory()) {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(fileContent);
            myWriter.close();
        }
    }

    public static boolean deleteFile(String filePath) {
        File f = new File(filePath);
        return f.delete();
    }

}
