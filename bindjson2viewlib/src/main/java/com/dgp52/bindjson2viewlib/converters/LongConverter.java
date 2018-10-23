package com.dgp52.bindjson2viewlib.converters;


import com.dgp52.bindjson2viewlib.interfaces.Convert;

import java.lang.reflect.Method;

public class LongConverter implements Convert {
    @Override
    public Object convert(String value, Method method) {
        return Long.parseLong(value);
    }
}
