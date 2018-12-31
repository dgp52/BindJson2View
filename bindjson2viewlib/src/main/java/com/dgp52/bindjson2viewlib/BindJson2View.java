package com.dgp52.bindjson2viewlib;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.wrappers.URlWrapper;

import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;


public final class BindJson2View {

    private static BindJson2View instance;

    @NonNull
    public static BindJson2View getInstance () {
        if (instance == null) {
            synchronized (BindJson2View.class) {
                if(instance==null) {
                    instance = new BindJson2View();
                    ServiceException.logI("BindJson2View instantiated");
                }
            }
        }
        return instance;
    }

    @NonNull
    public URlWrapper useNetwork(String url_str) {
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            ServiceException.logE(e);
        }
        return new URlWrapper(url,GlobalApplication.getAppContext());
    }

}
