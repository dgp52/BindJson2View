package com.dgp52.bindjson2viewlib.util;

public final class ToClass {

    public static Class toClass (String className) {
        if(className.toLowerCase().equals("charsequence")) {
            return  CharSequence.class;
        } else if (className.toLowerCase().equals("bool")) {
            return boolean.class;
        } else if (className.toLowerCase().equals("int")) {
            return int.class;
        }
        return null;
    }
}
