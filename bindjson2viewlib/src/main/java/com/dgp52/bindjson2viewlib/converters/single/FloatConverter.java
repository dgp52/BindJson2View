package com.dgp52.bindjson2viewlib.converters.single;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class FloatConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra, View view) {
        return Float.parseFloat(value);
    }
}
