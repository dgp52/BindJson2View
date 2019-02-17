package com.dgp52.bindjson2viewlib.unit;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleUnit;

import java.lang.ref.WeakReference;

public class Percentage implements SingleUnit {

    @Override
    public Object convert(String value, WeakReference<View> wk) {
        return (int)(Integer.parseInt(value)/100.0f);
    }
}
