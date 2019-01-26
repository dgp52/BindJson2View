package com.dgp52.bindjson2viewlib.converters.single;

import android.text.TextUtils;

import com.dgp52.bindjson2viewlib.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.logexception.exceptions.InvalidExtraException;
import com.dgp52.bindjson2viewlib.mappers.StringToExtra;

public class HeightConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra) {
        if(TextUtils.isEmpty(extra)) {
            ServiceException.logE(new InvalidExtraException());
            return null;
        }
        int height =  GlobalApplication.getAppContext().getResources().getDisplayMetrics().heightPixels * Integer.parseInt(value);
        return StringToExtra.toExtra(Integer.toString(height),extra);
    }
}
