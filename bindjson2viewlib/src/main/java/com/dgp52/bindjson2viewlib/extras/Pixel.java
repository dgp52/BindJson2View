package com.dgp52.bindjson2viewlib.extras;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.Extra;

public class Pixel implements Extra {
    @Override
    public Object convert(String value, View view) {
        return Integer.parseInt(value);
    }
}
