package com.dgp52.bindjson2viewlib.mappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.converters.single.BooleanConverter;
import com.dgp52.bindjson2viewlib.converters.single.CharConverter;
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

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public final class StringToSingleConvert {
    private static Map<String,SingleConvert> mapConverters;

    static {
        mapConverters = new HashMap<>();
        mapConverters.put(Keyword.SingleConvert.BOOLEAN.getValue(),new BooleanConverter());
        mapConverters.put(Keyword.SingleConvert.BYTE.getValue(), new CharConverter());
        mapConverters.put(Keyword.SingleConvert.INT.getValue(), new IntConverter());
        mapConverters.put(Keyword.SingleConvert.CHAR.getValue(), new CharConverter());
        mapConverters.put(Keyword.SingleConvert.SHORT.getValue(), new ShortConverter());
        mapConverters.put(Keyword.SingleConvert.LONG.getValue(), new LongConverter());
        mapConverters.put(Keyword.SingleConvert.FLOAT.getValue(), new FloatConverter());
        mapConverters.put(Keyword.SingleConvert.DOUBLE.getValue(), new DoubleConverter());
        mapConverters.put(Keyword.SingleConvert.CHARSEQUENCE.getValue(), new StringConverter());
        mapConverters.put(Keyword.SingleConvert.STRING.getValue(), new StringConverter());
        mapConverters.put(Keyword.SingleConvert.WIDTH.getValue(), new WidthConverter());
        mapConverters.put(Keyword.SingleConvert.HEIGHT.getValue(), new HeightConverter());
    }

    public static Object toSingleConverter(String converter, String value, String unit, WeakReference<View> wk) throws Exception{
        return mapConverters.get(converter).convert(value, unit, wk);
    }
}
