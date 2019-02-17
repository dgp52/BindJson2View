package com.dgp52.bindjson2viewlib.mappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.MultiUnit;
import com.dgp52.bindjson2viewlib.interfaces.SingleUnit;
import com.dgp52.bindjson2viewlib.unit.Hex;
import com.dgp52.bindjson2viewlib.unit.Percentage;
import com.dgp52.bindjson2viewlib.unit.Pixel;
import com.dgp52.bindjson2viewlib.unit.Resource;
import com.dgp52.bindjson2viewlib.util.Keyword;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public final class StringToUnit {

    private static Map<String, SingleUnit> mapSingleUnit;
    private static Map<String, MultiUnit> mapMultiUnit;
    static {
        mapSingleUnit = new HashMap<>();
        mapSingleUnit.put(Keyword.Unit.PERCENTAGE.getValue(), new Percentage());
        mapSingleUnit.put(Keyword.Unit.PIXEL.getValue(), new Pixel());
        mapSingleUnit.put(Keyword.Unit.HEX.getValue(), new Hex());

        mapMultiUnit = new HashMap<>();
        mapMultiUnit.put(Keyword.Unit.RESOURCE.getValue(), new Resource());
    }

    public static Object toUnit (String value, String unit, WeakReference<View> wk) {
        return mapSingleUnit.get(unit).convert(value, wk);
    }

    public static Object toUnit(JSONArray jsonArray, String unit, WeakReference<View> wk) {
        if(mapSingleUnit.containsKey(unit)){
            try {
                return toUnit(jsonArray.getString(0),unit,wk);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mapMultiUnit.get(unit).convert(jsonArray,wk);
    }
}
