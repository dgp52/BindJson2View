package com.dgp52.bindjson2viewlib.processor;

import android.view.View;

import com.dgp52.bindjson2viewlib.app.JSONMethod;
import com.dgp52.bindjson2viewlib.mappers.StringToConvert;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ArgumentProcessor {

    public static ArrayList<Object> getObjects(ArrayList<JSONMethod.Argument> arguments, WeakReference<View> wk){
        ArrayList<Object> objects = new ArrayList<>();
        for(JSONMethod.Argument a : arguments)
            objects.addAll(StringToConvert.getConvert(a.getName()).apply(a.getValues(),wk));
        return objects;
    }
}
