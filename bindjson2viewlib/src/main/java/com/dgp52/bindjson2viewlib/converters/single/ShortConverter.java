package com.dgp52.bindjson2viewlib.converters.single;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class ShortConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra, View view) {
        return Short.parseShort(value);
    }
}
