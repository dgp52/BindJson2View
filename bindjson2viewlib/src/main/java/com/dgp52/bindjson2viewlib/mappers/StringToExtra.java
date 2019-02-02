package com.dgp52.bindjson2viewlib.mappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.extras.Hex;
import com.dgp52.bindjson2viewlib.extras.Percentage;
import com.dgp52.bindjson2viewlib.extras.Pixel;
import com.dgp52.bindjson2viewlib.interfaces.Extra;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.util.HashMap;
import java.util.Map;

public final class StringToExtra {

    private static Map<String, Extra> mapExtra;
    static {
        mapExtra = new HashMap<>();
        mapExtra.put(Keyword.HEX, new Hex());
        mapExtra.put(Keyword.PERCENTAGE, new Percentage());
        mapExtra.put(Keyword.PIXEL, new Pixel());
    }

    public static Object toExtra (String value, String extra, View view) {
        return mapExtra.get(extra).convert(value, view);
    }
}
