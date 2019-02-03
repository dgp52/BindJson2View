package com.dgp52.bindjson2viewlib.unit;

import android.graphics.Color;
import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.Unit;

public class Hex implements Unit {
    @Override
    public Object convert(String value, View view) {
        return Color.parseColor("#"+value);
    }
}
