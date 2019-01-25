package com.dgp52.bindjson2viewlib.mappers;

import com.dgp52.bindjson2viewlib.extras.Hex;
import com.dgp52.bindjson2viewlib.interfaces.Extra;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.util.HashMap;
import java.util.Map;

public final class StringToExtra {

    private static Map<String, Extra> mapExtra;
    static {
        mapExtra = new HashMap<>();
        mapExtra.put(Keyword.HEX, new Hex());
    }

    public static Object toExtra (String value, String extra) {
        return mapExtra.get(extra).convert(value);
    }
}
