package com.dgp52.bindjson2viewlib.mappers;

import android.view.ViewGroup;

import com.dgp52.bindjson2viewlib.converters.multi.ColorConverter;
import com.dgp52.bindjson2viewlib.converters.multi.ImageConverter;
import com.dgp52.bindjson2viewlib.converters.single.HeightConverter;
import com.dgp52.bindjson2viewlib.converters.single.WidthConverter;
import com.dgp52.bindjson2viewlib.util.Keyword;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public final class StringToClass {

    private static Map<String,Class> mapClass;
    static {
        mapClass = new HashMap<>();
        mapClass.put(Keyword.SingleConvert.BOOLEAN.getValue(),boolean.class);
        mapClass.put(Keyword.SingleConvert.BYTE.getValue(),byte.class);
        mapClass.put(Keyword.SingleConvert.INT.getValue(),int.class);
        mapClass.put(Keyword.SingleConvert.CHAR.getValue(),char.class);
        mapClass.put(Keyword.SingleConvert.SHORT.getValue(),short.class);
        mapClass.put(Keyword.SingleConvert.LONG.getValue(),long.class);
        mapClass.put(Keyword.SingleConvert.FLOAT.getValue(),float.class);
        mapClass.put(Keyword.SingleConvert.DOUBLE.getValue(),double.class);
        mapClass.put(Keyword.SingleConvert.CHARARRAY.getValue(), char[].class);
        mapClass.put(Keyword.SingleConvert.CHARSEQUENCE.getValue(),CharSequence.class);
        mapClass.put(Keyword.SingleConvert.STRING.getValue(),String.class);
        mapClass.put(Keyword.SingleConvert.WIDTH.getValue(), WidthConverter.class);
        mapClass.put(Keyword.SingleConvert.HEIGHT.getValue(), HeightConverter.class);
        mapClass.put(Keyword.SingleConvert.LAYOUTPARAMS.getValue(), ViewGroup.LayoutParams.class);
    }

    public static Class toClass (String className) {
        return mapClass.get(className);
    }

    public static Class<?>[] toClasses (JSONArray cls) throws JSONException {
        Class<?> classes[] = new Class[cls.length()];
        for(int i =0; i<cls.length(); i++){
            classes[i] = toClass(cls.getString(i));
        }
        return classes;
    }
}
