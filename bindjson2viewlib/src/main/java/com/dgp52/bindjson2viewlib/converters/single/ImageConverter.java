package com.dgp52.bindjson2viewlib.converters.single;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.util.Asset;

public class ImageConverter implements SingleConvert {
    @Override
    public Object convert(String value, String extra, View view) throws Exception {
        return Asset.getDrawable(Asset.getInputStream(value));
    }
}
