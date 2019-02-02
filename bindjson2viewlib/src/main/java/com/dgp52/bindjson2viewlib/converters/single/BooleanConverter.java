package com.dgp52.bindjson2viewlib.converters.single;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class BooleanConverter implements SingleConvert {

    @Override
    public Object convert(String value, String extra, View view) {
        return Boolean.parseBoolean(value);
    }
}
