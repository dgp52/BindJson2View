package com.dgp52.bindjson2viewlib.app;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.mappers.StringToClass;
import com.dgp52.bindjson2viewlib.thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.util.Keyword;
import com.dgp52.bindjson2viewlib.wrappers.LockWrapper;
import com.dgp52.bindjson2viewlib.wrappers.ValueWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class ViewProcessor {
    private static CustomThreadPoolExecutor viewProcessor;
    public static boolean indexingComplete = false;

    public static void addView(View view, String tag) {
        if(view!=null && tag!=null) {
            view.setTag(tag);
            addView(view);
        }
    }

    public static void addView(View v) {
        WeakReference<View> wk = new WeakReference<>(v);
        if(viewProcessor==null)
            viewProcessor = new CustomThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        viewProcessor.submit(() -> {
            Thread.currentThread().setName(Keyword.App.VIEWPROCESSORTHREAD.getValue());
            if(wk==null || wk.get() == null || wk.get().getTag() == null || !(wk.get().getTag() instanceof String))
                return;
            try{
                LockWrapper.getLock().lock();
                while (!indexingComplete)
                    LockWrapper.getDownloadCondition().await();
                JSONArray methods = IndexJson.getMethods(wk.get().getTag().toString());
                if(methods!=null){
                    for(int i=0;i<methods.length();i++){
                        JSONObject attr = methods.getJSONObject(i);
                        try{
                            Class<?>[] reflectedClasses = StringToClass.toClasses(attr.getJSONArray(Keyword.JSONProperty.PARAMS.getValue()));
                            Method reflectedMethod = wk.get().getClass().getMethod(attr.getString(Keyword.JSONProperty.NAME.getValue()), reflectedClasses);
                            Object[] obj = ValueWrapper.toObject(attr.getJSONArray(Keyword.JSONProperty.VALUES.getValue()),
                                    attr.getJSONArray(Keyword.JSONProperty.CONVERTS.getValue()),
                                    attr.has(Keyword.JSONProperty.UNIT.getValue()) ? attr.getString(Keyword.JSONProperty.UNIT.getValue()) : null,
                                    wk);
                            new Handler(Looper.getMainLooper()).post(() -> {
                                try {
                                    reflectedMethod.invoke(wk.get(), obj);
                                    ServiceException.logI(wk.get().getTag() + " " +reflectedMethod.getName() + " processed");
                                } catch (Exception e) {
                                    ServiceException.logE(e);
                                }
                            });
                        } catch(Exception e) {
                            ServiceException.logE(attr.getString(Keyword.JSONProperty.NAME.getValue()) + " - " + attr.getJSONArray(Keyword.JSONProperty.PARAMS.getValue()).toString(),e);
                        }
                    }
                }
            } catch (Exception e) {
                ServiceException.logE(e);
            } finally {
                LockWrapper.getLock().unlock();
            }
        });
    }
}
