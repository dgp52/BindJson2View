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

        //Primitive class
        mapClass.put(Keyword.PRIMITIVE_BOOLEAN,boolean.class);
        mapClass.put(Keyword.PRIMITIVE_BYTE,byte.class);
        mapClass.put(Keyword.PRIMITIVE_INT,int.class);
        mapClass.put(Keyword.PRIMITIVE_CHAR,char.class);
        mapClass.put(Keyword.PRIMITIVE_SHORT,short.class);
        mapClass.put(Keyword.PRIMITIVE_LONG,long.class);
        mapClass.put(Keyword.PRIMITIVE_FLOAT,float.class);
        mapClass.put(Keyword.PRIMITIVE_DOUBLE,double.class);

        //Array class
        mapClass.put(Keyword.CHARARRAY, char[].class);

        //class
        mapClass.put(Keyword.CHARSEQUENCE,CharSequence.class);
        mapClass.put(Keyword.STRING,String.class);

        //Converters
        mapClass.put(Keyword.COLOR, ColorConverter.class);
        mapClass.put(Keyword.IMAGE, ImageConverter.class);
        mapClass.put(Keyword.WIDTH, WidthConverter.class);
        mapClass.put(Keyword.HEIGHT, HeightConverter.class);
        mapClass.put(Keyword.LAYOUTPARAMS, ViewGroup.LayoutParams.class);
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
