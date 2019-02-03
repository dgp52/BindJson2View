package com.dgp52.bindjson2viewlib.mappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.unit.Hex;
import com.dgp52.bindjson2viewlib.unit.Percentage;
import com.dgp52.bindjson2viewlib.unit.Pixel;
import com.dgp52.bindjson2viewlib.interfaces.Unit;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.util.HashMap;
import java.util.Map;

public final class StringToUnit {

    private static Map<String, Unit> mapUnit;
    static {
        mapUnit = new HashMap<>();
        mapUnit.put(Keyword.HEX, new Hex());
        mapUnit.put(Keyword.PERCENTAGE, new Percentage());
        mapUnit.put(Keyword.PIXEL, new Pixel());
    }

    public static Object toUnit (String value, String unit, View view) {
        return mapUnit.get(unit).convert(value, view);
    }
}
