package com.dgp52.bindjson2viewlib.extras;

import android.graphics.Color;

import com.dgp52.bindjson2viewlib.interfaces.Extra;

public class Hex implements Extra {
    @Override
    public Object convert(String value) {
        return Color.parseColor(value.startsWith("#") ? value : "#"+value);
    }
}
