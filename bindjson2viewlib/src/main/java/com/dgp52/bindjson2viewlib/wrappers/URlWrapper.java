package com.dgp52.bindjson2viewlib.wrappers;

import android.content.Context;

import com.dgp52.bindjson2viewlib.Thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.FileManager;
import com.dgp52.bindjson2viewlib.util.NetworkDownloader;

import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class URlWrapper {
    private URL url;
    private final String FILE_NAME = "bindjson2view_jsonfile";
    private Context context;

    public URlWrapper(URL url, Context context) {
        this.url = url;
        this.context = context;
    }

    public void start() {
        new Thread(()-> {
            if(url!=null) {
                CustomThreadPoolExecutor networkProcessor = new CustomThreadPoolExecutor(1,1,0l,TimeUnit.MICROSECONDS,new LinkedBlockingQueue<>());
                Lock lock = new ReentrantLock();
                Condition jsonStringReady = lock.newCondition();
                AtomicReference<String> jsonString = new AtomicReference<>("");
                try{
                    networkProcessor.submit(() -> FileManager.createFile(FILE_NAME, context)).get();
                    networkProcessor.submit(() -> NetworkDownloader.tryDownload(url, s -> {
                        lock.lock();
                        jsonString.set(s);
                        jsonStringReady.signalAll();
                        lock.unlock();
                    })).get();
                    networkProcessor.submit(() -> {
                        try {
                            lock.lock();
                            while(jsonString.get()==null)
                                jsonStringReady.await(10,TimeUnit.SECONDS);
                        } catch (Exception e) {
                            ServiceException.logE(e);
                        } finally {
                            lock.unlock();
                        }
                    }).get();
                } catch (Exception e) {
                    ServiceException.logE(e);
                }
            }
        }).start();
    }
}