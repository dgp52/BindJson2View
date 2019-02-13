package com.dgp52.bindjson2viewlib.logexception;

import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ServiceException {
    private static List<LogException> logExceptions;
    private static String android_version = Integer.toString(Build.VERSION.SDK_INT);

    public static boolean showInfo = true;
    public static boolean showError = true;

    public enum LogType {
        INFO, ERROR
    }

    public static synchronized void logI(String message) {
        if(logExceptions==null)
            logExceptions = new ArrayList<>();
        if(showInfo)
            logExceptions.add(new LogException(LogType.INFO,message,android_version));
    }

    public static synchronized void logE(String message, Exception e) {
        if(logExceptions==null)
            logExceptions = new ArrayList<>();
        if(showError)
            logExceptions.add(new LogException(LogType.ERROR, message,android_version,e));
    }

    public static synchronized void logE(Exception e) {
        logE("",e);
    }

    /*Call this method when all the logs are added to the logExceptions list*/
    public static void printLog(String logcat) {
        getLog();
        for (LogException e : logExceptions) {
            Log.i(logcat, e.toString());
        }
    }

    /*Call this method when all the logs are added to the logExceptions list*/
    public static List<LogException> getLog () {
        if(logExceptions==null)
            logExceptions = new ArrayList<>();
        for (int i=0;i<logExceptions.size();i++) {
            if(logExceptions.get(i)==null || logExceptions.get(i).getLogType()==LogType.INFO && !showInfo){
                logExceptions.remove(i);
            } else if (logExceptions.get(i).getLogType()==LogType.ERROR && !showError) {
                logExceptions.remove(i);
            }
        }
        return logExceptions;
    }

    public static void clearLogs(){
        if(logExceptions!=null)
            logExceptions.clear();
    }
}
