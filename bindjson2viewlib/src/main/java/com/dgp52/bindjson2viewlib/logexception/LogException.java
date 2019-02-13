package com.dgp52.bindjson2viewlib.logexception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogException {
    private String message;
    private ServiceException.LogType logType;

    public LogException(ServiceException.LogType logType, String message, String android_version) {
        this.logType = logType;
        this.message = "Log-Type: Info"+"\n"+
                "Message: "+message+"\n"+
                "Thread: "+Thread.currentThread().getName()+"\n"+
                "Android-Version: "+android_version+"\n" +
                "Timestamp: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime())+"\n \n";
    }

    public LogException(ServiceException.LogType logType, String message, String android_version, Exception e) {
        this.logType = logType;
        Writer writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        this.message = "Log-Type: Error"+"\n"+
                "Message: "+message+"\n"+
                "Thread: "+Thread.currentThread().getName()+"\n"+
                "Android-Version: "+android_version+"\n" +
                "Timestamp: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime())+"\n" +
                "Exception Name: "+e.getClass().getSimpleName()+"\n"+
                "Exception Description: "+e.getMessage()+"\n"+
                "Stack Trace: "+writer.toString()+"\n \n";
    }

    public ServiceException.LogType getLogType() {
        return logType;
    }

    @Override
    public String toString() {
        return message;
    }
}
