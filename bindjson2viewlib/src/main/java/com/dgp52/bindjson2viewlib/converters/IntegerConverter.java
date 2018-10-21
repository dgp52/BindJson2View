package com.dgp52.bindjson2viewlib.converters;

import android.graphics.Color;

import com.dgp52.bindjson2viewlib.interfaces.Convert;

public class IntegerConverter implements Convert {

    @Override
    public Object convert(String value) {
        if(value.startsWith("#")) {
            return Color.parseColor(value);
        }
        return Integer.parseInt(value);
    }
}
