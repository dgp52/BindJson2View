package com.dgp52.bindjson2viewlib.mappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.converters.multi.ImageConverter;
import com.dgp52.bindjson2viewlib.converters.multi.LayoutParamsWidthHeight;
import com.dgp52.bindjson2viewlib.interfaces.MultiConvert;
import com.dgp52.bindjson2viewlib.util.Keyword;

import org.json.JSONArray;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public final class StringToMultiConvert {
    private static Map<String,MultiConvert> mapConverters;

    static {
        mapConverters = new HashMap<>();
        mapConverters.put(Keyword.ConvertType.IMAGE.getValue(), new ImageConverter());
        mapConverters.put(Keyword.ConvertType.LAYOUTPARAMSWIDTHHEIGHT.getValue(), new LayoutParamsWidthHeight());
    }

    public static Object toMultiConverter(String converter, JSONArray values, String unit, WeakReference<View> wk) throws Exception{
        return mapConverters.get(converter).convert(values, unit, wk);
    }
}
