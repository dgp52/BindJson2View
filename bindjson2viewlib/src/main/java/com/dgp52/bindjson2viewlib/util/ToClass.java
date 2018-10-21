package com.dgp52.bindjson2viewlib.util;

import java.util.HashMap;
import java.util.Map;

public final class ToClass {

    private static Map<String,Class> mapClass;
    static {
        mapClass = new HashMap<>();
        mapClass.put(Keyword.PRIMITIVE_BOOLEAN,boolean.class);
        mapClass.put(Keyword.PRIMITIVE_BYTE,byte.class);
        mapClass.put(Keyword.PRIMITIVE_INT,int.class);
        mapClass.put(Keyword.PRIMITIVE_CHAR,char.class);
        mapClass.put(Keyword.PRIMITIVE_SHORT,short.class);
        mapClass.put(Keyword.PRIMITIVE_LONG,long.class);
        mapClass.put(Keyword.PRIMITIVE_FLOAT,float.class);
        mapClass.put(Keyword.PRIMITIVE_DOUBLE,double.class);
        mapClass.put(Keyword.CHARSEQUENCE,CharSequence.class);
        mapClass.put(Keyword.STRING,String.class);
    }


    public static Class toClass (String className) {
        return mapClass.get(className);
    }
}
