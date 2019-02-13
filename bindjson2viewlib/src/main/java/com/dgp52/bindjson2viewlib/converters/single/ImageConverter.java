package com.dgp52.bindjson2viewlib.converters.single;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleConvert;
import com.dgp52.bindjson2viewlib.util.Asset;

import java.lang.ref.WeakReference;

public class ImageConverter implements SingleConvert {
    @Override
    public Object convert(String value, String unit, WeakReference<View> wk) throws Exception {
        return Asset.getDrawable(Asset.getInputStream(value));
    }
}
