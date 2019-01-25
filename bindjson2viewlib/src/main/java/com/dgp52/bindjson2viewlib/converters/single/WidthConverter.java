package com.dgp52.bindjson2viewlib.converters.single;

import com.dgp52.bindjson2viewlib.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

public class WidthConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra) {
        if(value.endsWith("%")) {
            int absoluteWidth =  GlobalApplication.getAppContext().getResources().getDisplayMetrics().widthPixels;
            return (int) ((Integer.parseInt(value.substring(0, value.length() - 1))/100.0f)*absoluteWidth);
        }
        return Integer.parseInt(value);
    }
}
