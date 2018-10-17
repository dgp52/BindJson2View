package com.dgp52.bindjson2viewlib.util;

import android.util.Log;
import android.view.View;

import com.dgp52.bindjson2viewlib.Thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.wrappers.LockWrapper;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class AttributeProcessor {
    private static CustomThreadPoolExecutor attributeProcessor;

    public static void addAttribute(View view) {
        if(attributeProcessor==null)
            attributeProcessor = new CustomThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        attributeProcessor.submit(() -> {
            try{
                LockWrapper.getLock().lock();
                Log.i("log-cat", "Before While");
                ServiceException.logI("Before While");
                while (LockWrapper.getDownloadFlag()){
                    Log.i("log-cat", "Before wait");
                    ServiceException.logI("Before wait");
                    LockWrapper.getDownloadCondition().await();
                    ServiceException.logI("After wait");
                    Log.i("log-cat", "after wait");
                }
            } catch (Exception e) {
                ServiceException.logE(e);
            } finally {
                LockWrapper.getLock().unlock();
            }
        });
    }
}
