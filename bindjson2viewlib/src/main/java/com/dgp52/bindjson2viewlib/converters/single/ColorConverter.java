package com.dgp52.bindjson2viewlib.converters.single;

import android.text.TextUtils;
import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.logexception.exceptions.InvalidUnitException;
import com.dgp52.bindjson2viewlib.mappers.StringToUnit;

import java.lang.ref.WeakReference;

public class ColorConverter implements SingleConvert {

    @Override
    public Object convert(String value, String unit, WeakReference<View> wk) throws Exception {
        if(TextUtils.isEmpty(unit)) {
            ServiceException.logE(new InvalidUnitException());
            return null;
        }
        return StringToUnit.toUnit(value,unit, wk);
    }
}
