package com.dgp52.bindjson2viewlib.app;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.processor.JsonProcessor;

import java.net.MalformedURLException;
import java.net.URL;
import java.lang.String;

public enum BindJson2View {
    INSTANCE;

    public JsonProcessor useNetwork(String url_str) {
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            ServiceException.logE(e);
        }
        return new JsonProcessor(url);
    }

    public JsonProcessor useLocal(String jsonString) {
        return new JsonProcessor(jsonString);
    }
}
