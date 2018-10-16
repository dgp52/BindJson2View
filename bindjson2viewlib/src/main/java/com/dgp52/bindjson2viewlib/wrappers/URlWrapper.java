package com.dgp52.bindjson2viewlib.wrappers;

import android.content.Context;

import com.dgp52.bindjson2viewlib.util.FileManager;
import com.dgp52.bindjson2viewlib.util.NetworkDownloader;

import java.net.URL;

public class URlWrapper {
    private URL url;
    private final String FILE_NAME = "bindjson2view_jsonfile";
    private Context context;

    public URlWrapper(URL url, Context context) {
        this.url = url;
        this.context = context;
    }

    public void start() {
        new Thread(()-> {
            if(url!=null) {
                FileManager.createFile(FILE_NAME,context);
                FileManager.writeContent(NetworkDownloader.tryDownload(url),FILE_NAME,context);
            }
        }).start();
    }
}