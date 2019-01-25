package com.dgp52.bindjson2viewlib.converters.single;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class IntConverter implements SingleConvert {

    @Override
    public Object convert(String value, String extra) {
        return Integer.parseInt(value);
    }
}
