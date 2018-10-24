package com.dgp52.bindjson2viewlib;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.DisplayMetrics;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.wrappers.URlWrapper;

import java.net.MalformedURLException;
import java.net.URL;

public class BindJson2View {

    private static BindJson2View instance;
    private Context context;
    private static DisplayMetrics displayMetrics;
    private static AssetManager assetManager;

    private BindJson2View(Context context) {
        this.context = context;
    }

    public static BindJson2View getInstance (Context context) {
        if (instance == null) {
            synchronized (BindJson2View.class) {
                if(instance==null) {
                    instance = new BindJson2View(context);
                    displayMetrics = context.getResources().getDisplayMetrics();
                    assetManager = context.getAssets();
                    ServiceException.logI("BindJson2View instantiated");
                }
            }
        }
        return instance;
    }

    public URlWrapper useNetwork(String url_str) {
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            ServiceException.logE(e);
        }
        return new URlWrapper(url,context);
    }

    public static DisplayMetrics getDisplayMetrics(){
        return displayMetrics;
    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }
}
