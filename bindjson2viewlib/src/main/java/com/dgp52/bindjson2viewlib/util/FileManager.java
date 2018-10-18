package com.dgp52.bindjson2viewlib.util;

import android.content.Context;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public final class FileManager {

    public static boolean fileExist(String fileName, Context context) {
        File file = context.getFileStreamPath(fileName);
        return file.exists();
    }

    public static boolean createFile(String fileName, Context context) {
        if(!fileExist(fileName, context)){
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
                fileOutputStream.close();
                ServiceException.logI("File successfully created " + fileName);
            } catch (Exception e) {
                ServiceException.logE(e);
            }
            return true;
        } else {
            ServiceException.logI("File exists " + fileName);
            return false;
        }
    }

    public static boolean writeContent(String jsonString, String fileName, Context context)  {
        if(fileExist(fileName,context)) {
            File file = context.getFileStreamPath(fileName);
            FileWriter writer = null;
            try {
                writer = new FileWriter(file, false);
                writer.write(jsonString);
                writer.flush();
                writer.close();
                ServiceException.logI("JSON string successfully written to file " + fileName);
            } catch (Exception e) {
                ServiceException.logE(e);
            }
            return true;
        } else {
            ServiceException.logI("File doesn't exist " + fileName);
            return false;
        }
    }

    public static String readContent(String fileName, Context context) {
        if(fileExist(fileName,context)) {
            FileInputStream fileInputStream = null;
            String content = "";
            try {
                fileInputStream = context.openFileInput(fileName);
                int d;
                while((d = fileInputStream.read())!= -1){
                    content = content + Character.toString((char)d);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content;
        } else {
            ServiceException.logI("File doesn't exist " + fileName);
            return "";
        }
    }
}
