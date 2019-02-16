package com.dgp52.bindjson2viewlib.mappers;

import android.view.ViewGroup;

import com.dgp52.bindjson2viewlib.converters.single.ColorConverter;
import com.dgp52.bindjson2viewlib.converters.multi.ImageConverter;
import com.dgp52.bindjson2viewlib.converters.single.HeightConverter;
import com.dgp52.bindjson2viewlib.converters.single.WidthConverter;
import com.dgp52.bindjson2viewlib.util.Keyword;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.widget.ActionBarOverlayLayout;

public final class StringToClass {

    private static Map<String,Class> mapClass;
    static {
        mapClass = new HashMap<>();
        mapClass.put(Keyword.ConvertType.BOOLEAN.getValue(),boolean.class);
        mapClass.put(Keyword.ConvertType.BYTE.getValue(),byte.class);
        mapClass.put(Keyword.ConvertType.INT.getValue(),int.class);
        mapClass.put(Keyword.ConvertType.CHAR.getValue(),char.class);
        mapClass.put(Keyword.ConvertType.SHORT.getValue(),short.class);
        mapClass.put(Keyword.ConvertType.LONG.getValue(),long.class);
        mapClass.put(Keyword.ConvertType.FLOAT.getValue(),float.class);
        mapClass.put(Keyword.ConvertType.DOUBLE.getValue(),double.class);
        mapClass.put(Keyword.ConvertType.CHARARRAY.getValue(), char[].class);
        mapClass.put(Keyword.ConvertType.CHARSEQUENCE.getValue(),CharSequence.class);
        mapClass.put(Keyword.ConvertType.STRING.getValue(),String.class);
        mapClass.put(Keyword.ConvertType.COLOR.getValue(), ColorConverter.class);
        mapClass.put(Keyword.ConvertType.IMAGE.getValue(), ImageConverter.class);
        mapClass.put(Keyword.ConvertType.WIDTH.getValue(), WidthConverter.class);
        mapClass.put(Keyword.ConvertType.HEIGHT.getValue(), HeightConverter.class);
        mapClass.put(Keyword.ConvertType.LAYOUTPARAMS.getValue(), ViewGroup.LayoutParams.class);
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
