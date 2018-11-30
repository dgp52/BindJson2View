package com.dgp52.bindjson2viewlib.converters.single;

import android.graphics.Color;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

import java.lang.reflect.Method;

public class ColorConverter implements SingleConvert {

    @Override
    public Object convert(String value) {
        return value.startsWith("#") ? Color.parseColor(value) : null;
    }
}
