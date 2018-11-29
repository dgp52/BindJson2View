package com.dgp52.bindjson2viewlib.wrappers;

import com.dgp52.bindjson2viewlib.mappers.ToClass;
import com.dgp52.bindjson2viewlib.mappers.ToMultiConvert;
import com.dgp52.bindjson2viewlib.mappers.ToSingleConvert;

import org.json.JSONArray;

public class ValueWrapper {

    public static Object[] toObject (JSONArray values, JSONArray converts) throws Exception {
        int resultObjSize = values.length();
        Object[] resultObj;
        if(resultObjSize == converts.length()) {
            resultObj = new Object[resultObjSize];
            for(int i =0; i<values.length(); i++){
                resultObj[i] = ToSingleConvert.toSingleConverter(ToClass.toClass(converts.getString(i)), values.getString(i));
            }
        } else {
            resultObj = new Object[1];
            resultObj[0] = ToMultiConvert.toMultiConverter(ToClass.toClass(converts.getString(0)), values);
        }
        return resultObj;
    }
}
