package com.dgp52.bindjson2viewlib.converters;

import com.dgp52.bindjson2viewlib.interfaces.Convert;

public class DoubleConverter implements Convert {
    @Override
    public Object convert(String value) {
        return Double.parseDouble(value);
    }
}
