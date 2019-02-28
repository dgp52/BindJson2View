package com.dgp52.bindjson2viewlib.interfaces;

import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public interface ArgumentFunction {
    ArrayList<Object> apply(ArrayList<String> values, WeakReference<View> wk);
}
