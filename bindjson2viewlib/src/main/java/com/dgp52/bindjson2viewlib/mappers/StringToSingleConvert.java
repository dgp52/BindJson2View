package com.dgp52.bindjson2viewlib.mappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.converters.single.BooleanConverter;
import com.dgp52.bindjson2viewlib.converters.single.CharConverter;
import com.dgp52.bindjson2viewlib.converters.single.ColorConverter;
import com.dgp52.bindjson2viewlib.converters.single.DoubleConverter;
import com.dgp52.bindjson2viewlib.converters.single.FloatConverter;
import com.dgp52.bindjson2viewlib.converters.single.HeightConverter;
import com.dgp52.bindjson2viewlib.converters.single.IntConverter;
import com.dgp52.bindjson2viewlib.converters.single.LongConverter;
import com.dgp52.bindjson2viewlib.converters.single.ShortConverter;
import com.dgp52.bindjson2viewlib.converters.single.StringConverter;
import com.dgp52.bindjson2viewlib.converters.single.WidthConverter;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.util.HashMap;
import java.util.Map;

public final class StringToSingleConvert {
    private static Map<String,SingleConvert> mapConverters;

    static {
        mapConverters = new HashMap<>();
        mapConverters.put(Keyword.BOOLEAN,new BooleanConverter());
        mapConverters.put(Keyword.BYTE, new CharConverter());
        mapConverters.put(Keyword.INT, new IntConverter());
        mapConverters.put(Keyword.CHAR, new CharConverter());
        mapConverters.put(Keyword.SHORT, new ShortConverter());
        mapConverters.put(Keyword.LONG, new LongConverter());
        mapConverters.put(Keyword.FLOAT, new FloatConverter());
        mapConverters.put(Keyword.DOUBLE, new DoubleConverter());
        mapConverters.put(Keyword.CHARSEQUENCE, new StringConverter());
        mapConverters.put(Keyword.STRING, new StringConverter());
        mapConverters.put(Keyword.COLOR, new ColorConverter());
        mapConverters.put(Keyword.WIDTH, new WidthConverter());
        mapConverters.put(Keyword.HEIGHT, new HeightConverter());
    }

    public static Object toSingleConverter(String converter, String value, String unit, View view) throws Exception{
        return mapConverters.get(converter).convert(value, unit, view);
    }
}
