package com.dgp52.bindjson2viewlib.converters.single;

import android.text.TextUtils;
import android.view.View;

import com.dgp52.bindjson2viewlib.app.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.logexception.exceptions.InvalidUnitException;
import com.dgp52.bindjson2viewlib.mappers.StringToUnit;
import com.dgp52.bindjson2viewlib.util.Keyword;

import java.lang.ref.WeakReference;

public class WidthConverter implements SingleConvert {
    @Override
    public Object convert(String value, String unit, WeakReference<View> wk) {
        if(TextUtils.isEmpty(unit)) {
            ServiceException.logE(new InvalidUnitException());
            return null;
        }
        if(unit.equals(Keyword.PERCENTAGE))
            value =  Integer.toString(GlobalApplication.getAppContext().getResources().getDisplayMetrics().widthPixels * Integer.parseInt(value));
        return StringToUnit.toUnit(value,unit,wk);
    }
}
