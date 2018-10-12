package com.dgp52.bindjson2viewlib.wrappers;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;

import java.net.URL;

public class URlWrapper {
    private URL url;

    public URlWrapper(URL url) {
        this.url = url;
    }

    public void start() {
        new Thread(()-> {
                ServiceException.logI(url.getHost());
                ServiceException.logI("URLWrapper Run method");
            }).start();
    }
}
