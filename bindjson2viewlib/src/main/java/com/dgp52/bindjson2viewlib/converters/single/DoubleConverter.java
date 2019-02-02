package com.dgp52.bindjson2viewlib.converters.single;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class DoubleConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra, View view) {
        return Double.parseDouble(value);
    }
}
