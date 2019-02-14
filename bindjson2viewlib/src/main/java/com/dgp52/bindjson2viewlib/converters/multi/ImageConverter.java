package com.dgp52.bindjson2viewlib.converters.multi;

import android.view.View;

import com.dgp52.bindjson2viewlib.app.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.MultiConvert;
import com.dgp52.bindjson2viewlib.util.Resource;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

public class ImageConverter implements MultiConvert {

    @Override
    public Object convert(JSONArray jsonArray, String unit, WeakReference<View> wk) throws Exception{
        return Resource.getResource(jsonArray.getString(0), jsonArray.getString(1), GlobalApplication.getAppContext().getPackageName());
    }
}
