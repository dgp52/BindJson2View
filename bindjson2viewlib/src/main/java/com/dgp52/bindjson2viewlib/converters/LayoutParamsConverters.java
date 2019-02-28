package com.dgp52.bindjson2viewlib.converters;

import android.view.View;

import com.dgp52.bindjson2viewlib.interfaces.ArgumentFunction;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;

public enum LayoutParamsConverters implements ArgumentFunction {

    LAYOUTPARAMSWIDTHHEIGHTPIXEL {
        @Override
        public ArrayList<Object> apply(ArrayList<String> values, WeakReference<View> wk) {
            if(values==null || values.isEmpty() || wk.get()==null)
                return null;
            ArrayList<Object> objects = new ArrayList<>();
            setWidthHeight(values,wk);
            objects.add(wk.get().getLayoutParams());
            return objects;
        }
    },
    LAYOUTPARAMSWIDTHHEIGHTPERCENTAGE {
        @Override
        public ArrayList<Object> apply(ArrayList<String> values, WeakReference<View> wk) {
            if(values==null || values.isEmpty() || wk.get()==null)
                return null;
            ArrayList<Object> objects = new ArrayList<>();
            values.set(0, IntConverters.PERCENTAGEWIDTH.apply(values,wk).get(0).toString());
            values.set(1, IntConverters.PERCENTAGEHEIGHT.apply(values,wk).get(1).toString());
            setWidthHeight(values,wk);
            objects.add(wk.get().getLayoutParams());
            return objects;
        }
    };

    public void setWidthHeight(ArrayList<String> values, WeakReference<View> wk) {
        try {
            Field wField = wk.get().getLayoutParams().getClass().getField("width");
            Field hField = wk.get().getLayoutParams().getClass().getField("height");
            wField.setInt(wk.get().getLayoutParams(), Integer.parseInt(values.get(0)));
            hField.setInt(wk.get().getLayoutParams(), Integer.parseInt(values.get(1)));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
