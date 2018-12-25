package com.dgp52.bindjson2viewlib;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.util.DisplayMetrics;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.wrappers.URlWrapper;

import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class BindJson2View extends ContentProvider {

    private static BindJson2View instance;
    private Context context;
    private static DisplayMetrics displayMetrics;

    private BindJson2View(Context context) {
        this.context = context;
    }

    public BindJson2View(){

    }

    public static BindJson2View getInstance (Context context) {
        if (instance == null) {
            synchronized (BindJson2View.class) {
                if(instance==null) {
                    instance = new BindJson2View(context);
                    displayMetrics = context.getResources().getDisplayMetrics();
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

    @Override
    public boolean onCreate() {
        Context context = getContext();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public void attachInfo(Context context, ProviderInfo info){
        Context c = context;
        ProviderInfo p = info;
    }
}
