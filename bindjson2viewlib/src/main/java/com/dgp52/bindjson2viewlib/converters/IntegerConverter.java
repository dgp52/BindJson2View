package com.dgp52.bindjson2viewlib.converters;

import android.graphics.Color;
import android.util.DisplayMetrics;

import com.dgp52.bindjson2viewlib.BindJson2View;
import com.dgp52.bindjson2viewlib.interfaces.Convert;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.lang.reflect.Method;
import java.util.Map;

public class IntegerConverter implements Convert {

    @Override
    public Object convert(String value, Method method) {
        if(value.startsWith("#"))
            return Color.parseColor(value);
        if(value.endsWith("%"))
            return percentageToPixel(value,method);
        return Integer.parseInt(value);
    }

    private int percentageToPixel(String value, Method method){
        int absoluteSize = method.getName().equals(Keyword.SETHEIGHT)?BindJson2View.getDisplayMetrics().heightPixels:BindJson2View.getDisplayMetrics().widthPixels;
        return (int) ((Integer.parseInt(value.substring(0, value.length() - 1))/100.0f)*absoluteSize);
    }
}
