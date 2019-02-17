package com.dgp52.bindjson2viewlib.interfaces;

import android.view.View;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

public interface MultiUnit {
    Object convert(JSONArray values, WeakReference<View> wk);
}
