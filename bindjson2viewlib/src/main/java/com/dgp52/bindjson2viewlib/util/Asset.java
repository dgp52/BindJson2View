package com.dgp52.bindjson2viewlib.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.dgp52.bindjson2viewlib.GlobalApplication;

import java.io.IOException;
import java.io.InputStream;

public class Asset {

    public static InputStream getInputStream (String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = GlobalApplication.getAppContext().getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static Drawable getDrawable (InputStream inputStream) {
        return Drawable.createFromStream(inputStream, null);
    }

    public static Bitmap getBitmap (InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

}
