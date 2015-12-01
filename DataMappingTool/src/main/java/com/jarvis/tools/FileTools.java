package com.jarvis.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by C5023792 on 12/1/2015.
 */
public class FileTools {

    public static List<File> getAllFiles(File path) {
        List<File> fileList = new ArrayList<>();
        File[] files = path.listFiles();
        fileList = Arrays.asList(files);
        return fileList;
    }

    public static List<File> getAllFiles(String path) {
        File folder = new File(path);
        return getAllFiles(folder);
    }

    public static void copyFiles(String source, String destination) {
        copyFiles(new File(source), new File(destination));
    }

    public static void copyFiles(File source, File destination) {

    }
}
