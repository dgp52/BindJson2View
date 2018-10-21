package com.dgp52.bindjson2viewlib.util;

import com.dgp52.bindjson2viewlib.interfaces.Convert;

public final class BooleanConverter implements Convert{

    @Override
    public Object convert(String value) {
        return Boolean.parseBoolean(value);
    }
}
