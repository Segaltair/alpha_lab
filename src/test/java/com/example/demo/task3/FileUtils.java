package com.example.demo.task3;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils {
    private static File resultFile = null;

    public static File createFile(String filename) {

        try {
            String filepath = FileUtils.class.getResource("/").getPath() + "task3/";
            File dir = new File(filepath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File newFile = new File(filepath + filename);
            if (newFile.createNewFile()) {
                System.out.println("File created: " + filename);
            } else {
                FileChannel.open(Paths.get(newFile.toURI()), StandardOpenOption.WRITE)
                        .truncate(0)
                        .close();
                System.out.println("File already created, cleaned up " + filename);
            }
            return newFile;
        } catch (IOException e) {
            throw new RuntimeException("Creating file error");
        }
    }

    public static File getResultFile(String resultFilename) {
        if (resultFile == null) {
            String filepath = FileUtils.class.getResource("/task3/").getPath() + resultFilename;
            resultFile = new File(filepath);
        }
        return resultFile;
    }
}
