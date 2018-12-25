package com.dgp52.bindjson2viewlib.converters.multi;

import com.dgp52.bindjson2viewlib.interfaces.MultiConvert;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;

import org.json.JSONArray;

import java.lang.reflect.Method;

public class ResourceConverter implements MultiConvert {

    @Override
    public Object convert(JSONArray jsonArray) {


        return new Object();
    }
}
