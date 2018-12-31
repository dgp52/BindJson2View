package com.dgp52.bindjson2viewlib.converters.single;

import com.dgp52.bindjson2viewlib.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class HeightConverter implements SingleConvert {
    @Override
    public Object convert(String value) {
        if(value.endsWith("%")) {
            int absoluteHeight =  GlobalApplication.getAppContext().getResources().getDisplayMetrics().heightPixels;
            return (int) ((Integer.parseInt(value.substring(0, value.length() - 1))/100.0f)*absoluteHeight);
        }
        return Integer.parseInt(value);
    }
}
