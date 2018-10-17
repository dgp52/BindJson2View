package com.dgp52.bindjson2viewlib.util;

import android.view.View;

import com.dgp52.bindjson2viewlib.Thread.CustomThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class AttributeProcessor {
    private static CustomThreadPoolExecutor attributeProcessor;

    public static void addAttribute(View view) {
        if(attributeProcessor==null)
            attributeProcessor = new CustomThreadPoolExecutor(1,1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }
}
