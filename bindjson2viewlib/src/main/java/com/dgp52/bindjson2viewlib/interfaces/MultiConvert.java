package com.dgp52.bindjson2viewlib.interfaces;

import org.json.JSONArray;

public interface MultiConvert {
    Object convert(JSONArray jsonArray) throws Exception;
}
