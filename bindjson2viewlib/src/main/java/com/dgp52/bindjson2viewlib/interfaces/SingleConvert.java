package com.dgp52.bindjson2viewlib.interfaces;

import android.view.View;

public interface SingleConvert {
    Object convert(String value, String extra, View view) throws Exception;
}
