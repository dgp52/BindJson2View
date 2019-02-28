package com.dgp52.bindjson2viewlib.processor;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.dgp52.bindjson2viewlib.app.JSONMethod;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.mappers.StringToClass;
import com.dgp52.bindjson2viewlib.thread.CustomThreadPoolExecutor;
import com.dgp52.bindjson2viewlib.util.Keyword;
import com.dgp52.bindjson2viewlib.wrappers.LockWrapper;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
                ArrayList<JSONMethod> methods = IndexJsonProcessor.getMethods(wk.get().getTag().toString());
                if(methods!=null){
                    for(JSONMethod m : methods ){
                        try{
                            ArrayList<Class<?>> classes = StringToClass.toClasses(m.getParams());
                            Method reflectedMethod = wk.get().getClass().getMethod(m.getName(), classes.toArray(new Class<?>[classes.size()]));
                            ArrayList<Object> objects = ArgumentProcessor.getObjects(m.getArguments(), wk);
                            new Handler(Looper.getMainLooper()).post(() -> {
                                try {
                                    reflectedMethod.invoke(wk.get(), objects.toArray(new Object[objects.size()]));
                                    ServiceException.logI(wk.get().getTag() + " " +reflectedMethod.getName() + " processed");
                                } catch (Exception e) {
                                    ServiceException.logE(e);
                                }
                            });
                        } catch(Exception e) {
                            ServiceException.logE(m.getName() + " - " + m.getParams(),e);
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
