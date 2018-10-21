package com.dgp52.bindjson2viewlib.converters;

import com.dgp52.bindjson2viewlib.interfaces.Convert;

public class CharConverter implements Convert {
    @Override
    public Object convert(String value) {
        return value.charAt(0);
    }
}
