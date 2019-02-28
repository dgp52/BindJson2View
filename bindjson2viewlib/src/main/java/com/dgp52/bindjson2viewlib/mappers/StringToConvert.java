package com.dgp52.bindjson2viewlib.mappers;

import com.dgp52.bindjson2viewlib.converters.BooleanConverters;
import com.dgp52.bindjson2viewlib.converters.ByteConverters;
import com.dgp52.bindjson2viewlib.converters.CharConverters;
import com.dgp52.bindjson2viewlib.converters.ColorConverters;
import com.dgp52.bindjson2viewlib.converters.DoubleConverters;
import com.dgp52.bindjson2viewlib.converters.FloatConverters;
import com.dgp52.bindjson2viewlib.converters.IntConverters;
import com.dgp52.bindjson2viewlib.converters.LayoutParamsConverters;
import com.dgp52.bindjson2viewlib.converters.LongConverters;
import com.dgp52.bindjson2viewlib.converters.ShortConverters;
import com.dgp52.bindjson2viewlib.converters.StringConverters;
import com.dgp52.bindjson2viewlib.interfaces.ArgumentFunction;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.util.HashMap;
import java.util.Map;

public class StringToConvert {
    private static Map<java.lang.String, ArgumentFunction> mapConverters;
    static {
        mapConverters = new HashMap<>();
        mapConverters.put(Keyword.Convert.INT.getValue(), IntConverters.INT);
        mapConverters.put(Keyword.Convert.BOOLEAN.getValue(), BooleanConverters.BOOLEAN);
        mapConverters.put(Keyword.Convert.BYTE.getValue(), ByteConverters.BYTE);
        mapConverters.put(Keyword.Convert.CHAR.getValue(), CharConverters.CHAR);
        mapConverters.put(Keyword.Convert.SHORT.getValue(), ShortConverters.SHORT);
        mapConverters.put(Keyword.Convert.LONG.getValue(), LongConverters.LONG);
        mapConverters.put(Keyword.Convert.FLOAT.getValue(), FloatConverters.FLOAT);
        mapConverters.put(Keyword.Convert.DOUBLE.getValue(), DoubleConverters.DOUBLE);
        mapConverters.put(Keyword.Convert.CHARSEQUENCE.getValue(), StringConverters.STRING);
        mapConverters.put(Keyword.Convert.STRING.getValue(), StringConverters.STRING);
        mapConverters.put(Keyword.Convert.CHARARRAY.getValue(), StringConverters.TOCHARARRAY);
        mapConverters.put(Keyword.Convert.TOCHARARRAY.getValue(), StringConverters.TOCHARARRAY);
        mapConverters.put(Keyword.Convert.PARSECOLOR.getValue(), ColorConverters.PARSECOLOR);
        mapConverters.put(Keyword.Convert.PERCENTAGEWIDTH.getValue(), IntConverters.PERCENTAGEWIDTH);
        mapConverters.put(Keyword.Convert.PERCENTAGEHEIGHT.getValue(), IntConverters.PERCENTAGEHEIGHT);
        mapConverters.put(Keyword.Convert.RESOURCEID.getValue(), IntConverters.RESOURCEID);
        mapConverters.put(Keyword.Convert.LAYOUTPARAMSWIDTHHEIGHTPIXEL.getValue(), LayoutParamsConverters.LAYOUTPARAMSWIDTHHEIGHTPIXEL);
        mapConverters.put(Keyword.Convert.LAYOUTPARAMSWIDTHHEIGHTPERCENTAGE.getValue(), LayoutParamsConverters.LAYOUTPARAMSWIDTHHEIGHTPERCENTAGE);
    }

    public static ArgumentFunction getConvert(java.lang.String convertName){
        return mapConverters.get(convertName);
    }
}
