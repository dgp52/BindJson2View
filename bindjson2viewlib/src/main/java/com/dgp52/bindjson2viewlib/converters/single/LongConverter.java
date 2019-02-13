package com.dgp52.bindjson2viewlib.converters.single;


import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

import java.lang.ref.WeakReference;

public class LongConverter implements SingleConvert {
    @Override
    public Object convert(String value, String unit,WeakReference<View> wk) {
        return Long.parseLong(value);
    }
}
