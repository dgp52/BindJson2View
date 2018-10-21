package com.dgp52.bindjson2viewlib.util;

import java.util.HashMap;
import java.util.Map;

public final class ToClass {

    private static Map<String,Class> mapClass;
    static {
        mapClass = new HashMap<>();
        mapClass.put("bool",boolean.class);
        mapClass.put("int",int.class);
    }


    public static Class toClass (String className) {
        return mapClass.get(className);
    }
}
