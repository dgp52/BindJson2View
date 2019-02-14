package com.dgp52.bindjson2viewlib.util;

import com.dgp52.bindjson2viewlib.app.GlobalApplication;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.logexception.exceptions.ResourceNotFoundException;

public class Resource {

    public static int getResource(String resName, String resFolder, String packageName) {
        int resourceId =  GlobalApplication.getAppContext().getResources().getIdentifier(resName,resFolder,packageName);
        if(resourceId == 0)
            ServiceException.logE(new ResourceNotFoundException("Unable to find resource: " + resName));
        return resourceId;
    }

}
