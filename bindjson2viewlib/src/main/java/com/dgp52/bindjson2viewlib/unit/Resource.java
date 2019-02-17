package com.dgp52.bindjson2viewlib.unit;

import android.view.View;

import com.dgp52.bindjson2viewlib.app.GlobalApplication;
import com.dgp52.bindjson2viewlib.interfaces.MultiUnit;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.logexception.exceptions.ResourceNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;

public class Resource implements MultiUnit {
    @Override
    public Object convert(JSONArray values, WeakReference<View> wk) {
        int resourceId = 0;
        try {
            String resourceName = values.getString(0);
            String resourceFolder = values.getString(1);
            resourceId = GlobalApplication.getAppContext().getResources().getIdentifier(resourceName,resourceFolder,GlobalApplication.getAppContext().getPackageName());
            if(resourceId == 0)
                ServiceException.logE(new ResourceNotFoundException("Unable to find resource: " + resourceName));
        } catch (JSONException e) {
            ServiceException.logE(e);
        }
        return resourceId;
    }
}
