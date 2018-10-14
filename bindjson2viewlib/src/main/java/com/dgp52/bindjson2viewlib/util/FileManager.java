package com.dgp52.bindjson2viewlib.util;

import android.content.Context;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;

import java.io.File;
import java.io.FileOutputStream;

public class FileManager {

    public static boolean fileExist(String fileName, Context context) {
        File file = context.getFileStreamPath(fileName);
        return file.exists();
    }

    public static boolean createFile(String fileName, Context context) throws Exception {
        if(!fileExist(fileName, context)){
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            fileOutputStream.close();
            ServiceException.logI("File successfully created " + fileName);
            return true;
        } else {
            ServiceException.logI("File exists " + fileName);
            return false;
        }
    }
}
