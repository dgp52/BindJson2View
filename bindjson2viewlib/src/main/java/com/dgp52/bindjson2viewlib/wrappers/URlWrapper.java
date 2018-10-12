package com.dgp52.bindjson2viewlib.wrappers;

import android.util.Log;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;

import java.net.URL;

public class URlWrapper implements Runnable {
    private URL url;

    public URlWrapper(URL url) {
        this.url = url;
    }

    @Override
    public void run() {
        ServiceException.logI(url.getHost());
        ServiceException.logI("URLWrapper Run method");
    }
}
