package com.dgp52.bindjson2viewlib.util;

import android.content.Context;

import java.io.File;

public class FileManager {
    private String previousFileName;
    private String currentFileName;

    public static boolean fileExist(String fileName, Context context) {
        File file = context.getFileStreamPath(fileName);
        return file.exists();
    }
}
