package com.dgp52.bindjson2viewlib.unit;

import android.graphics.Color;
import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.SingleUnit;

import java.lang.ref.WeakReference;

public class Hex  implements SingleUnit {
    @Override
    public Object convert(String value, WeakReference<View> wk) {
        return Color.parseColor("#" + value);
    }
}
