package com.dgp52.bindjson2viewlib.converters;

import com.dgp52.bindjson2viewlib.interfaces.Convert;

public class ByteConverter implements Convert {
    @Override
    public Object convert(String value) {
        return Byte.parseByte(value);
    }
}
