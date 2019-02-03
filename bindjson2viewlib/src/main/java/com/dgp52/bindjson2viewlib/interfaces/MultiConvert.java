package com.dgp52.bindjson2viewlib.interfaces;

import android.view.View;

import org.json.JSONArray;

public interface MultiConvert {
    Object convert(JSONArray jsonArray, String unit, View view) throws Exception;
}
