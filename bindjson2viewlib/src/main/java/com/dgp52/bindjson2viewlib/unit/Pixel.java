package com.dgp52.bindjson2viewlib.unit;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.Unit;

import java.lang.ref.WeakReference;

public class Pixel implements Unit {
    @Override
    public Object convert(String value, WeakReference<View> wk) {
        return Integer.parseInt(value);
    }
}
