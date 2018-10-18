package com.dgp52.bindjson2viewlib.util;

import android.view.View;

import com.dgp52.bindjson2viewlib.Thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.wrappers.LockWrapper;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class AttributeProcessor {
    private static CustomThreadPoolExecutor attributeProcessor;

    public static void addAttribute(View view, String tag) {
        if(view!=null && tag!=null) {
            view.setTag(tag);
            addAttribute(view);
        }
    }

    public static void addAttribute(View view) {
        if(view==null || !(view.getTag() instanceof String))
            return;
        if(attributeProcessor==null)
            attributeProcessor = new CustomThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        attributeProcessor.submit(() -> {
            try{
                LockWrapper.getLock().lock();
                while (!LockWrapper.getDownloadFlag()){
                    LockWrapper.getDownloadCondition().await();
                }
                ServiceException.logI(view.getTag().toString() + " Added");
            } catch (Exception e) {
                ServiceException.logE(e);
            } finally {
                LockWrapper.getLock().unlock();
            }
        });
    }
}
