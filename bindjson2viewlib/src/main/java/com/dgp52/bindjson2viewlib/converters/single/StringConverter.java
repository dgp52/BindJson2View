package com.dgp52.bindjson2viewlib.converters.single;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class StringConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra) {
        return value;
    }
}
