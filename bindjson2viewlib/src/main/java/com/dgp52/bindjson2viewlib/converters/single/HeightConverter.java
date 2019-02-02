package com.dgp52.bindjson2viewlib.converters.single;

import android.text.TextUtils;
import android.view.View;

import com.dgp52.bindjson2viewlib.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.logexception.exceptions.InvalidExtraException;
import com.dgp52.bindjson2viewlib.mappers.StringToExtra;
import com.dgp52.bindjson2viewlib.util.Keyword;

public class HeightConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra, View view) {
        if(TextUtils.isEmpty(extra)) {
            ServiceException.logE(new InvalidExtraException());
            return null;
        }
        if(extra.equals(Keyword.PERCENTAGE))
            value =  Integer.toString(GlobalApplication.getAppContext().getResources().getDisplayMetrics().heightPixels * Integer.parseInt(value));
        return StringToExtra.toExtra(value,extra, view);
    }
}
