package com.dgp52.bindjson2viewlib.converters.single;

import android.text.TextUtils;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.logexception.exceptions.InvalidExtraException;
import com.dgp52.bindjson2viewlib.mappers.StringToExtra;

public class ColorConverter implements SingleConvert {

    @Override
    public Object convert(String value, String extra) throws Exception {
        if(TextUtils.isEmpty(extra)) {
            ServiceException.logE(new InvalidExtraException());
            return null;
        }
        return StringToExtra.toExtra(value,extra);
    }
}
