package com.dgp52.bindjson2viewlib.mappers;

import android.view.ViewGroup;

import com.dgp52.bindjson2viewlib.util.Keyword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class StringToClass {

    private static Map<String,Class> mapClass;
    static {
        mapClass = new HashMap<>();
        mapClass.put(Keyword.Convert.BOOLEAN.getValue(),boolean.class);
        mapClass.put(Keyword.Convert.BYTE.getValue(),byte.class);
        mapClass.put(Keyword.Convert.INT.getValue(),int.class);
        mapClass.put(Keyword.Convert.CHAR.getValue(),char.class);
        mapClass.put(Keyword.Convert.SHORT.getValue(),short.class);
        mapClass.put(Keyword.Convert.LONG.getValue(),long.class);
        mapClass.put(Keyword.Convert.FLOAT.getValue(),float.class);
        mapClass.put(Keyword.Convert.DOUBLE.getValue(),double.class);
        mapClass.put(Keyword.Convert.CHARARRAY.getValue(), char[].class);
        mapClass.put(Keyword.Convert.CHARSEQUENCE.getValue(),CharSequence.class);
        mapClass.put(Keyword.Convert.STRING.getValue(),String.class);
        mapClass.put(Keyword.Convert.LAYOUTPARAMS.getValue(), ViewGroup.LayoutParams.class);
    }

    public static Class toClass (String className) {
        return mapClass.get(className);
    }

    public static ArrayList<Class<?>> toClasses (ArrayList<String> cls) {
        ArrayList<Class<?>> classes = new ArrayList<>();
        for(String s : cls)
            classes.add(toClass((s)));
        return classes;
    }
}
