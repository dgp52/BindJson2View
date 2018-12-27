package com.dgp52.bindjson2viewlib;

import android.app.Application;
import android.content.Context;

public class GlobalApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalApplication.appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}