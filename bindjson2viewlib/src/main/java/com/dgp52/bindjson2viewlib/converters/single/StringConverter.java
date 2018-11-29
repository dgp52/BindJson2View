package com.dgp52.bindjson2viewlib.converters.single;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

import java.lang.reflect.Method;

public class StringConverter implements SingleConvert {
    @Override
    public Object convert(String value) {
        return value;
    }
}
