package com.dgp52.bindjson2viewlib.converters;

import com.dgp52.bindjson2viewlib.interfaces.Convert;

public class FloatConverter implements Convert {
    @Override
    public Object convert(String value) {
        return Float.parseFloat(value);
    }
}
