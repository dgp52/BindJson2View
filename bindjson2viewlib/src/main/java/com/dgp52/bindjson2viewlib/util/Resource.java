package com.dgp52.bindjson2viewlib.util;

import android.content.Context;

public class Resource {

    public static int getResource(Context context, String resName, String resFolder) {
        return  context.getResources().getIdentifier(resName,resFolder,context.getPackageName());
    }

}
