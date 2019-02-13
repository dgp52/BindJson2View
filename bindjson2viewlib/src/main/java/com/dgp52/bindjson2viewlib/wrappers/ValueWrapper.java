package com.dgp52.bindjson2viewlib.wrappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.mappers.StringToMultiConvert;
import com.dgp52.bindjson2viewlib.mappers.StringToSingleConvert;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

public class ValueWrapper {

    public static Object[] toObject (JSONArray values, JSONArray converts, String unit, WeakReference<View> wk) throws Exception {
        int resultObjSize = values.length();
        Object[] resultObj;
        if(resultObjSize == converts.length()) {
            resultObj = new Object[resultObjSize];
            for(int i =0; i<values.length(); i++){
                resultObj[i] = StringToSingleConvert.toSingleConverter(converts.getString(i), values.getString(i), unit, wk);
            }
        } else {
            resultObj = new Object[1];
            resultObj[0] = StringToMultiConvert.toMultiConverter(converts.getString(0), values, unit, wk);
        }
        return resultObj;
    }
}
