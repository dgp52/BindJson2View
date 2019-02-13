package com.dgp52.bindjson2viewlib.interfaces;

import android.view.View;

import java.lang.ref.WeakReference;

public interface SingleConvert {
    Object convert(String value, String unit, WeakReference<View> wk) throws Exception;
}
