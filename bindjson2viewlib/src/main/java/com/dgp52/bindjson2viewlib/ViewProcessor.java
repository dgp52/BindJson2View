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
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class ViewProcessor {
    private static CustomThreadPoolExecutor viewProcessor;
    public static String jsonString;

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
            Thread.currentThread().setName(Keyword.VIEW_PROCESSOR_THREAD);
            if(wk==null || wk.get() == null || wk.get().getTag() == null || !(wk.get().getTag() instanceof String))
                return;
            try{
                LockWrapper.getLock().lock();
                while (jsonString==null)
                    LockWrapper.getDownloadCondition().await();
                JSONArray binders = new JSONObject(jsonString).getJSONArray(Keyword.BINDERS);
                JSONArray methods = null;
                viewloop:
                for(int i=0;i<binders.length();i++){
                    JSONArray tags = binders.getJSONObject(i).getJSONArray(Keyword.TAGS);
                    for(int j=0;j<tags.length();j++){
                        if(tags.getString(j).equals(wk.get().getTag().toString())){
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
                            Method reflectedMethod = wk.get().getClass().getMethod(attr.getString(Keyword.NAME), reflectedClasses);
                            Object[] obj = ValueWrapper.toObject(attr.getJSONArray(Keyword.VALUES),
                                    attr.getJSONArray(Keyword.CONVERTS),
                                    attr.has(Keyword.UNIT) ? attr.getString(Keyword.UNIT) : null,
                                    wk);
                            new Handler(Looper.getMainLooper()).post(() -> {
                                try {
                                    reflectedMethod.invoke(wk.get(), obj);
                                    ServiceException.logI(wk.get().getTag() + " processed");
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
