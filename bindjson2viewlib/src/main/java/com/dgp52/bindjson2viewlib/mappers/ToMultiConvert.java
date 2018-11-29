package com.dgp52.bindjson2viewlib.mappers;

import com.dgp52.bindjson2viewlib.converters.multi.ResourceConverter;
import com.dgp52.bindjson2viewlib.interfaces.MultiConvert;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public final class ToMultiConvert {
    private static Map<Class,MultiConvert> mapConverters;

    static {
        mapConverters = new HashMap<>();
        mapConverters.put(ResourceConverter.class, new ResourceConverter());
    }

    public static Object toMultiConverter(Class c, JSONArray values) {
        return mapConverters.get(c).convert(values);
    }
}
