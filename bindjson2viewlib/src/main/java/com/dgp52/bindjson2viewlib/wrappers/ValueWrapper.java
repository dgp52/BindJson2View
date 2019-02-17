package com.dgp52.bindjson2viewlib.wrappers;

import android.view.View;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.mappers.StringToMultiConvert;
import com.dgp52.bindjson2viewlib.mappers.StringToSingleConvert;
import com.dgp52.bindjson2viewlib.util.Keyword;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

public class ValueWrapper {

    public static Object[] toObject (JSONArray values, JSONArray converts, String unit, WeakReference<View> wk) throws Exception {
        boolean isMultiConverter = false;
        if(converts.length()==1) {
            String converter = converts.getString(0);
            for(Keyword.MultiConvert m: Keyword.MultiConvert.values()){
                if(m.getValue().equals(converter)) {
                    isMultiConverter = true;
                    break;
                }
            }
        }

        int resultObjSize = values.length();
        Object[] resultObj;
        if(!isMultiConverter && resultObjSize == converts.length()) {
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
