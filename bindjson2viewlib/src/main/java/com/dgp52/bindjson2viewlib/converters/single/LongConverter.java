package com.dgp52.bindjson2viewlib.converters.single;


import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class LongConverter implements SingleConvert {
    @Override
    public Object convert(String value, String unit, View view) {
        return Long.parseLong(value);
    }
}
