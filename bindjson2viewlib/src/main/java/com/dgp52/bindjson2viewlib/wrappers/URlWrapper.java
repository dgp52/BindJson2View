package com.dgp52.bindjson2viewlib.wrappers;

import android.content.Context;

import com.dgp52.bindjson2viewlib.ViewProcessor;
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
                    ViewProcessor.jsonString = NetworkDownloader.tryDownload(url);
                    FileManager.writeContent(ViewProcessor.jsonString,Keyword.FILE_NAME,context);
                    LockWrapper.getLock().lock();
                    LockWrapper.getDownloadCondition().signalAll();
                } catch (Exception e) {
                    ServiceException.logE(e);
                } finally {
                    LockWrapper.getLock().unlock();
                }
            }
        }).start();
    }
}