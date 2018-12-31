package com.dgp52.bindjson2viewlib.mappers;

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

import java.util.HashMap;
import java.util.Map;

public final class ToSingleConvert {
    private static Map<Class,SingleConvert> mapConverters;

    static {
        mapConverters = new HashMap<>();
        mapConverters.put(boolean.class,new BooleanConverter());
        mapConverters.put(byte.class, new CharConverter());
        mapConverters.put(int.class, new IntConverter());
        mapConverters.put(char.class, new CharConverter());
        mapConverters.put(short.class, new ShortConverter());
        mapConverters.put(long.class, new LongConverter());
        mapConverters.put(float.class, new FloatConverter());
        mapConverters.put(double.class, new DoubleConverter());
        mapConverters.put(CharSequence.class, new StringConverter());
        mapConverters.put(String.class, new StringConverter());
        mapConverters.put(ColorConverter.class, new ColorConverter());
        mapConverters.put(WidthConverter.class, new WidthConverter());
        mapConverters.put(HeightConverter.class, new HeightConverter());
    }

    public static Object toSingleConverter(Class c, String value) throws Exception{
        return mapConverters.get(c).convert(value);
    }
}
