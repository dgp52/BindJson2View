package com.dgp52.bindjson2viewlib;

import android.os.Looper;
import android.os.Handler;
import android.view.View;

import com.dgp52.bindjson2viewlib.Thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.Keyword;
import com.dgp52.bindjson2viewlib.util.ToClass;
import com.dgp52.bindjson2viewlib.util.ToObject;
import com.dgp52.bindjson2viewlib.wrappers.LockWrapper;

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
                        JSONObject method = attributes.getJSONObject(i);
                        try{
                            Class reflectedClass = ToClass.toClass(method.getString(Keyword.TYPE));
                            Method reflectedMethod = view.getClass().getMethod(method.getString(Keyword.METHOD).startsWith(Keyword.SET) ? method.getString(Keyword.METHOD) : Keyword.SET+method.getString(Keyword.METHOD), reflectedClass);
                            String value = method.getString(Keyword.VALUE);
                            new Handler(Looper.getMainLooper()).post(() -> {
                                try {
                                        reflectedMethod.invoke(view, ToObject.toObject(reflectedClass, value));
                                    ServiceException.logI(reflectedMethod.getName()+" invoked on "+view.getTag());
                                } catch (Exception e) {
                                    ServiceException.logE(e);
                                }
                            });
                        } catch(Exception e) {
                            ServiceException.logE(e);
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
