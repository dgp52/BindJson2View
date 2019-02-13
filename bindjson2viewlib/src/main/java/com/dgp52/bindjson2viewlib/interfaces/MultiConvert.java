package com.dgp52.bindjson2viewlib.interfaces;

import android.view.View;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

public interface MultiConvert {
    Object convert(JSONArray jsonArray, String unit, WeakReference<View> wk) throws Exception;
}
