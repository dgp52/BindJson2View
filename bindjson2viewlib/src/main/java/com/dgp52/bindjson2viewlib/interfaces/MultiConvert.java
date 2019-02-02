package com.dgp52.bindjson2viewlib.interfaces;

import android.view.View;

import org.json.JSONArray;

public interface MultiConvert {
    Object convert(JSONArray jsonArray, String extra, View view) throws Exception;
}
