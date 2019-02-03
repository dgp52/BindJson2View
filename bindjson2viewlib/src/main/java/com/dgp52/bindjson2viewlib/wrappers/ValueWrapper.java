package com.dgp52.bindjson2viewlib.wrappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.mappers.StringToClass;
import com.dgp52.bindjson2viewlib.mappers.ClassToMultiConvert;
import com.dgp52.bindjson2viewlib.mappers.ClassToSingleConvert;

import org.json.JSONArray;

public class ValueWrapper {

    public static Object[] toObject (JSONArray values, JSONArray converts, String unit, View view) throws Exception {
        int resultObjSize = values.length();
        Object[] resultObj;
        if(resultObjSize == converts.length()) {
            resultObj = new Object[resultObjSize];
            for(int i =0; i<values.length(); i++){
                resultObj[i] = ClassToSingleConvert.toSingleConverter(StringToClass.toClass(converts.getString(i)), values.getString(i), unit, view);
            }
        } else {
            resultObj = new Object[1];
            resultObj[0] = ClassToMultiConvert.toMultiConverter(StringToClass.toClass(converts.getString(0)), values, unit, view);
        }
        return resultObj;
    }
}
