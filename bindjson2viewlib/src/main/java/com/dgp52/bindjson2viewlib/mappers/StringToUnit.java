package com.dgp52.bindjson2viewlib.mappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.unit.Hex;
import com.dgp52.bindjson2viewlib.unit.Percentage;
import com.dgp52.bindjson2viewlib.unit.Pixel;
import com.dgp52.bindjson2viewlib.interfaces.Unit;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public final class StringToUnit {

    private static Map<String, Unit> mapUnit;
    static {
        mapUnit = new HashMap<>();
        mapUnit.put(Keyword.Unit.HEX.getValue(), new Hex());
        mapUnit.put(Keyword.Unit.PERCENTAGE.getValue(), new Percentage());
        mapUnit.put(Keyword.Unit.PIXEL.getValue(), new Pixel());
    }

    public static Object toUnit (String value, String unit, WeakReference<View> wk) {
        return mapUnit.get(unit).convert(value, wk);
    }
}
