package com.dgp52.bindjson2viewlib.interfaces;

import android.view.View;

import java.lang.ref.WeakReference;

public interface Unit {
    Object convert(String value, WeakReference<View> wk);
}
