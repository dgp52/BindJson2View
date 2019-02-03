package com.dgp52.bindjson2viewlib.unit;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.Unit;

public class Pixel implements Unit {
    @Override
    public Object convert(String value, View view) {
        return Integer.parseInt(value);
    }
}
