package com.dgp52.bindjson2viewlib.converters;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.ArgumentFunction;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public enum ByteConverters implements ArgumentFunction {
    BYTE {
        @Override
        public ArrayList<Object> apply(ArrayList<String> values, WeakReference<View> wk) {
            if(values==null || values.isEmpty())
                return null;
            ArrayList<Object> objects = new ArrayList<>();
            for (String s: values)
                objects.add(Byte.parseByte(s));
            return objects;
        }
    }
}
