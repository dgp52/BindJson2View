package com.dgp52.bindjson2viewlib.converters.single;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class ByteConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra) {
        return Byte.parseByte(value);
    }
}
