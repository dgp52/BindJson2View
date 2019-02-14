package com.dgp52.bindjson2viewlib.converters.multi;

import android.text.TextUtils;
import android.view.View;

import com.dgp52.bindjson2viewlib.app.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.MultiConvert;
import com.dgp52.bindjson2viewlib.mappers.StringToUnit;
import com.dgp52.bindjson2viewlib.util.Keyword;

import org.json.JSONArray;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class LayoutParamsWidthHeight implements MultiConvert {
    @Override
    public Object convert(JSONArray jsonArray, String unit, WeakReference<View> wk) throws Exception {
        if(TextUtils.isEmpty(unit)){
            //TODO throw exception
            return null;
        }
        if(wk.get()!=null) {
            int width = jsonArray.getInt(0);
            int height = jsonArray.getInt(1);
            if(unit.equals(Keyword.PERCENTAGE)) {
                width = GlobalApplication.getAppContext().getResources().getDisplayMetrics().widthPixels * width;
                height = GlobalApplication.getAppContext().getResources().getDisplayMetrics().heightPixels * height;
            }
            width = (int)StringToUnit.toUnit(Integer.toString(width),unit,wk);
            height = (int)StringToUnit.toUnit(Integer.toString(height),unit,wk);
            Field wField = wk.get().getLayoutParams().getClass().getField(Keyword.WIDTH);
            Field hField = wk.get().getLayoutParams().getClass().getField(Keyword.HEIGHT);
            wField.setInt(wk.get().getLayoutParams(), width);
            hField.setInt(wk.get().getLayoutParams(), height);
            return wk.get().getLayoutParams();
        }
        return null;
    }
}
