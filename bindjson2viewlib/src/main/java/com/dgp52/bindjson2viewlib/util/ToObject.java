package com.dgp52.bindjson2viewlib.util;

import com.dgp52.bindjson2viewlib.converters.BooleanConverter;
import com.dgp52.bindjson2viewlib.converters.CharConverter;
import com.dgp52.bindjson2viewlib.converters.DoubleConverter;
import com.dgp52.bindjson2viewlib.converters.FloatConverter;
import com.dgp52.bindjson2viewlib.converters.IntegerConverter;
import com.dgp52.bindjson2viewlib.converters.LongConverter;
import com.dgp52.bindjson2viewlib.converters.ShortConverter;
import com.dgp52.bindjson2viewlib.converters.StringConverter;
import com.dgp52.bindjson2viewlib.interfaces.Convert;

import java.util.HashMap;
import java.util.Map;

public final class ToObject {
    private static Map<Class,Convert> mapObjects;

    static {
        mapObjects = new HashMap<>();
        mapObjects.put(boolean.class,new BooleanConverter());
        mapObjects.put(byte.class, new CharConverter());
        mapObjects.put(int.class, new IntegerConverter());
        mapObjects.put(char.class, new CharConverter());
        mapObjects.put(short.class, new ShortConverter());
        mapObjects.put(long.class, new LongConverter());
        mapObjects.put(float.class, new FloatConverter());
        mapObjects.put(double.class, new DoubleConverter());
        mapObjects.put(CharSequence.class, new StringConverter());
        mapObjects.put(String.class, new StringConverter());
    }

    public static Object toObject(Class c, String value) {
        return mapObjects.get(c).convert(value);
    }
}
