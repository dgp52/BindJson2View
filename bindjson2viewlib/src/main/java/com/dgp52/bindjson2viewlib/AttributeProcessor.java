package com.dgp52.bindjson2viewlib;

import android.os.Looper;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

import com.dgp52.bindjson2viewlib.thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.Keyword;
import com.dgp52.bindjson2viewlib.mappers.ToClass;
import com.dgp52.bindjson2viewlib.wrappers.LockWrapper;
import com.dgp52.bindjson2viewlib.wrappers.ValueWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
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
                JSONArray attributes = null;
                JSONArray views = new JSONObject(jsonString).getJSONArray(Keyword.VIEWS);
                viewloop:
                for(int i=0;i<views.length();i++){
                    JSONArray tags = views.getJSONObject(i).getJSONArray(Keyword.TAGS);
                    for(int j=0;j<tags.length();j++){
                        if(tags.getString(j).equals(view.getTag().toString())){
                            attributes = views.getJSONObject(i).getJSONArray(Keyword.ATTRIBUTES);
                            break viewloop;
                        }
                    }
                }
                if(attributes!=null){
                    for(int i=0;i<attributes.length();i++){
                        JSONObject attr = attributes.getJSONObject(i);
                        try{
                            Class<?>[] reflectedClasses = ToClass.toClasses(attr.getJSONArray(Keyword.PARAMS));
                            Method reflectedMethod = view.getClass().getMethod(attr.getString(Keyword.METHOD), reflectedClasses);
                            Object[] obj = ValueWrapper.toObject(attr.getJSONArray(Keyword.VALUES),attr.getJSONArray(Keyword.CONVERTS));
                            new Handler(Looper.getMainLooper()).post(() -> {
                                try {
                                    reflectedMethod.invoke(view, obj);
                                    ServiceException.logI(reflectedMethod.getName()+" invoked on "+view.getTag());
                                } catch (Exception e) {
                                    ServiceException.logE(e);
                                }
                            });
                        } catch(Exception e) {
                            ServiceException.logE(attr.getString(Keyword.METHOD) + " - " + attr.getJSONArray(Keyword.PARAMS).toString(),e);
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
