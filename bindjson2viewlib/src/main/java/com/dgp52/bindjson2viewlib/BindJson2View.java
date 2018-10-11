package com.dgp52.bindjson2viewlib;

import android.content.Context;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;

public class BindJson2View {

    private static BindJson2View instance;

    private Context context;
    private String fileName, url, jsonString;

    private BindJson2View(Context context) {
        this.context = context;
    }

    public static BindJson2View getInstance (Context context) {
        if (instance == null) {
            synchronized (BindJson2View.class) {
                if(instance==null) {
                    instance = new BindJson2View(context);
                    ServiceException.logI("BindJson2View instantiated");
                }
            }
        }
        return instance;
    }

    public BindJson2View fileName(String fileName){
        if(fileName!=null) {
            this.fileName = fileName;
        } else {
            this.fileName = "bindjson2view";
        }
        ServiceException.logI("Filename added " + fileName);
        return this;
    }

    public BindJson2View url(String url) {
        if(url!=null) {
            this.url = url;
            ServiceException.logI("URL added");
        }
        return this;
    }

    public BindJson2View jsonString(String jsonString) {
        if(jsonString!=null) {
            this.jsonString = jsonString;
            ServiceException.logI("JsonString added");
        }
        return this;
    }
}
