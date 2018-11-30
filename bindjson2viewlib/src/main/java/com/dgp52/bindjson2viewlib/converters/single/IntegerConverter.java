package com.dgp52.bindjson2viewlib.converters.single;

import android.graphics.Color;

import com.dgp52.bindjson2viewlib.BindJson2View;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.lang.reflect.Method;

public class IntegerConverter implements SingleConvert {

    @Override
    public Object convert(String value) {
        return Integer.parseInt(value);
    }

    private int percentageToPixel(String value){
//        if(method.getName().equals(Keyword.SETHEIGHT) || method.getName().equals(Keyword.SETWidth)) {
//            int absoluteSize = method.getName().equals(Keyword.SETHEIGHT)?BindJson2View.getDisplayMetrics().heightPixels:BindJson2View.getDisplayMetrics().widthPixels;
//            return (int) ((Integer.parseInt(value.substring(0, value.length() - 1))/100.0f)*absoluteSize);
//        }
        return 0;
    }
}
