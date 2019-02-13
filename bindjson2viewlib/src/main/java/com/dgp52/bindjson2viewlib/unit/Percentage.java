package com.dgp52.bindjson2viewlib.unit;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.Unit;

import java.lang.ref.WeakReference;

public class Percentage implements Unit {

    private int convertedValue;

    @Override
    public Object convert(String value, WeakReference<View> wk) {
        convertedValue = (int)(Integer.parseInt(value)/100.0f);
        return convertedValue;
    }

    public int getConvertedValue() {
        return convertedValue;
    }
}
