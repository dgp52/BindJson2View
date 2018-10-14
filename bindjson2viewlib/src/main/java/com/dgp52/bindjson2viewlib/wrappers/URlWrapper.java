package com.dgp52.bindjson2viewlib.wrappers;

import android.content.Context;

import com.dgp52.bindjson2viewlib.Thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.FileManager;

import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class URlWrapper {
    private URL url;
    private final String FILE_NAME = "bindjson2view_jsonfile";
    private Context context;
    private static CustomThreadPoolExecutor networkProcessor = null;

    public URlWrapper(URL url, Context context) {
        this.url = url;
        this.context = context;
    }

    public void start() {
        new Thread(()-> {
            if(url!=null) {
                try{
                    if(networkProcessor==null)
                        networkProcessor = new CustomThreadPoolExecutor(1,1,0l,TimeUnit.MICROSECONDS,new LinkedBlockingQueue<>());
                    networkProcessor.submit(() -> FileManager.createFile(FILE_NAME, context)).get();
                } catch (Exception e) {
                    ServiceException.logE(e);
                }
            }
        }).start();
    }
}