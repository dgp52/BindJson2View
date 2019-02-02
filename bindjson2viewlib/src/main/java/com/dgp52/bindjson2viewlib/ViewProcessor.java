package com.dgp52.bindjson2viewlib;

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
    private static CustomThreadPoolExecutor attributeProcessor;
    public static String jsonString;

    public static void addView(View view, String tag) {
        if(view!=null && tag!=null) {
            view.setTag(tag);
            addView(view);
        }
    }

    public static void addView(View view) {
        WeakReference<View> weakReference = new WeakReference<>(view);
        if(weakReference == null || weakReference.get() == null ||
                weakReference.get().getTag() == null || !(weakReference.get().getTag() instanceof String))
            return;
        if(attributeProcessor==null)
            attributeProcessor = new CustomThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        attributeProcessor.submit(() -> {
            try{
                if(weakReference == null || weakReference.get()==null)
                    return;
                LockWrapper.getLock().lock();
                while (jsonString==null)
                    LockWrapper.getDownloadCondition().await();
                JSONArray methods = null;
                JSONArray binders = new JSONObject(jsonString).getJSONArray(Keyword.BINDERS);
                viewloop:
                for(int i=0;i<binders.length();i++){
                    JSONArray tags = binders.getJSONObject(i).getJSONArray(Keyword.TAGS);
                    for(int j=0;j<tags.length();j++){
                        if(tags.getString(j).equals(weakReference.get().getTag().toString())){
                            methods = binders.getJSONObject(i).getJSONArray(Keyword.METHODS);
                            break viewloop;
                        }
                    }
                }
                if(methods!=null){
                    for(int i=0;i<methods.length();i++){
                        JSONObject attr = methods.getJSONObject(i);
                        try{
                            Class<?>[] reflectedClasses = StringToClass.toClasses(attr.getJSONArray(Keyword.PARAMS));
                            Method reflectedMethod = weakReference.get().getClass().getMethod(attr.getString(Keyword.NAME), reflectedClasses);
                            Object[] obj = ValueWrapper.toObject(attr.getJSONArray(Keyword.VALUES),
                                    attr.getJSONArray(Keyword.CONVERTS),
                                    attr.has(Keyword.EXTRA) ? attr.getString(Keyword.EXTRA) : null,
                                    weakReference.get());
                            new Handler(Looper.getMainLooper()).post(() -> {
                                try {
                                    reflectedMethod.invoke(weakReference.get(), obj);
                                    ServiceException.logI(weakReference.get().getTag() + " serviced by thread " + Thread.currentThread().getName());
                                } catch (Exception e) {
                                    ServiceException.logE(e);
                                }
                            });
                        } catch(Exception e) {
                            ServiceException.logE(attr.getString(Keyword.NAME) + " - " + attr.getJSONArray(Keyword.PARAMS).toString(),e);
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
