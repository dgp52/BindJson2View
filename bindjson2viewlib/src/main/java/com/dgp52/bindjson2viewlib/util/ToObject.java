package com.dgp52.bindjson2viewlib.util;

import android.graphics.Color;

import com.dgp52.bindjson2viewlib.interfaces.Convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ToObject {
    private static Map<Class,Convert> mapObjects;

    static {
        mapObjects = new HashMap<>();
        mapObjects.put(boolean.class,new BooleanConverter());
        mapObjects.put(int.class, new IntegerConverter());
    }

    public static Object toObject(Class c, String value) {
        return mapObjects.get(c).convert(value);
    }

//    public static Object toObject( Class c, String value ) {
//        if(boolean.class == c)
//            return Boolean.parseBoolean(value);
//        if(byte.class == c )
//            return Byte.parseByte(value);
//        if(short.class == c )
//            return Short.parseShort(value);
//        if(int.class == c )
//            return value.startsWith("#")? Color.parseColor(value):Integer.parseInt(value);
//        if(long.class == c )
//            return Long.parseLong(value);
//        if(float.class == c )
//            return Float.parseFloat(value);
//        if(double.class == c )
//            return Double.parseDouble(value);
//        return value;
//    }


}
