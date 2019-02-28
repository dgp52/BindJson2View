package com.dgp52.bindjson2viewlib.converters;


import android.view.View;

import com.dgp52.bindjson2viewlib.app.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.ArgumentFunction;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public enum IntConverters implements ArgumentFunction {

    INT {
        @Override
        public ArrayList<Object> apply(ArrayList<String> values, WeakReference<View> wk) {
            if(values==null || values.isEmpty())
                return null;
            ArrayList<Object> objects = new ArrayList<>();
            for (String s: values)
                objects.add(Integer.parseInt(s));
            return objects;
        }
    },
    PERCENTAGEWIDTH {
        @Override
        public ArrayList<Object> apply(ArrayList<String> values, WeakReference<View> wk) {
            if(values==null || values.isEmpty())
                return null;
            ArrayList<Object> objects = new ArrayList<>();
            for (String s: values)
                objects.add((int)((GlobalApplication.getAppContext().getResources().getDisplayMetrics().widthPixels * Integer.parseInt(s))/100.0f));
            return objects;
        }
    },
    PERCENTAGEHEIGHT {
        @Override
        public ArrayList<Object> apply(ArrayList<String> values, WeakReference<View> wk) {
            if(values==null || values.isEmpty())
                return null;
            ArrayList<Object> objects = new ArrayList<>();
            for (String s: values)
                objects.add((int)((GlobalApplication.getAppContext().getResources().getDisplayMetrics().heightPixels * Integer.parseInt(s))/100.0f));
            return objects;
        }
    },
    RESOURCEID {
        @Override
        public ArrayList<Object> apply(ArrayList<String> values, WeakReference<View> wk) {
            if(values==null || values.isEmpty())
                return null;
            ArrayList<Object> objects = new ArrayList<>();
            objects.add(GlobalApplication.getAppContext().getResources().getIdentifier(values.get(0),values.get(1),GlobalApplication.getAppContext().getPackageName()));
            return objects;
        }
    }
}
