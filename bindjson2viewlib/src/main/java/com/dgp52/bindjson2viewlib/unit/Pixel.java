package com.dgp52.bindjson2viewlib.unit;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleUnit;

import java.lang.ref.WeakReference;

public class Pixel implements SingleUnit {
    @Override
    public Object convert(String value, WeakReference<View> wk) {
        return Integer.parseInt(value);
    }
}
