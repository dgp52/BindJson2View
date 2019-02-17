package com.dgp52.bindjson2viewlib.converters.multi;

import android.text.TextUtils;
import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.MultiConvert;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.logexception.exceptions.InvalidUnitException;
import com.dgp52.bindjson2viewlib.mappers.StringToUnit;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

public class ColorConverter implements MultiConvert {

    @Override
    public Object convert(JSONArray jsonArray, String unit, WeakReference<View> wk) throws Exception {
        if(TextUtils.isEmpty(unit)) {
            ServiceException.logE(new InvalidUnitException());
            return null;
        }
        return StringToUnit.toUnit(jsonArray,unit, wk);
    }
}
