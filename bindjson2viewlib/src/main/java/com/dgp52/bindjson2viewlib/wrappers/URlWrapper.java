package com.dgp52.bindjson2viewlib.wrappers;

import android.content.Context;

import com.dgp52.bindjson2viewlib.app.IndexJson;
import com.dgp52.bindjson2viewlib.app.ViewProcessor;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.FileManager;
import com.dgp52.bindjson2viewlib.util.Keyword;
import com.dgp52.bindjson2viewlib.util.NetworkDownloader;

import java.net.URL;

public class URlWrapper {
    private URL url;
    private Context context;

    public URlWrapper(URL url, Context context) {
        this.url = url;
        this.context = context;
    }

    public void start() {
        new Thread(()-> {
            if(url!=null) {
                try {
                    FileManager.createFile(Keyword.FILE_NAME,context);
                    String jsonString = NetworkDownloader.tryDownload(url);
                    ViewProcessor.indexingComplete = IndexJson.Index(jsonString);
                    LockWrapper.getLock().lock();
                    LockWrapper.getDownloadCondition().signalAll();
                    FileManager.writeContent(jsonString,Keyword.FILE_NAME,context);
                } catch (Exception e) {
                    ServiceException.logE(e);
                } finally {
                    LockWrapper.getLock().unlock();
                }
            }
        }).start();
    }
}