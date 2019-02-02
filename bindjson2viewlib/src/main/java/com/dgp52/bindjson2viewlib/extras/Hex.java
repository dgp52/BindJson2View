package com.dgp52.bindjson2viewlib.extras;

import android.graphics.Color;
import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.Extra;

public class Hex implements Extra {
    @Override
    public Object convert(String value, View view) {
        return Color.parseColor("#"+value);
    }
}
