package com.dgp52.bindjson2viewlib.mappers;

import com.dgp52.bindjson2viewlib.converters.multi.ImageConverter;
import com.dgp52.bindjson2viewlib.interfaces.MultiConvert;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public final class ClassToMultiConvert {
    private static Map<Class,MultiConvert> mapConverters;

    static {
        mapConverters = new HashMap<>();
        mapConverters.put(ImageConverter.class, new ImageConverter());
    }

    public static Object toMultiConverter(Class c, JSONArray values) throws Exception{
        return mapConverters.get(c).convert(values);
    }
}
