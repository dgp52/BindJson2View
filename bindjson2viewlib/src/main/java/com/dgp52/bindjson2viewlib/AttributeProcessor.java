package com.dgp52.bindjson2viewlib;

import android.view.View;

import com.dgp52.bindjson2viewlib.BindJson2View;
import com.dgp52.bindjson2viewlib.Thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.FileManager;
import com.dgp52.bindjson2viewlib.util.Keyword;
import com.dgp52.bindjson2viewlib.wrappers.LockWrapper;

import org.json.JSONArray;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class AttributeProcessor {
    private static CustomThreadPoolExecutor attributeProcessor;
    public static String jsonString;

    public static void addAttribute(View view, String tag) {
        if(view!=null && tag!=null) {
            view.setTag(tag);
            addAttribute(view);
        }
    }

    public static void addAttribute(View view) {
        if(view==null || view.getTag()==null || !(view.getTag() instanceof String))
            return;
        if(attributeProcessor==null)
            attributeProcessor = new CustomThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        attributeProcessor.submit(() -> {
            try{
                LockWrapper.getLock().lock();
                while (jsonString==null){
                    LockWrapper.getDownloadCondition().await();
                }
            } catch (Exception e) {
                ServiceException.logE(e);
            } finally {
                LockWrapper.getLock().unlock();
            }
        });
    }
}
