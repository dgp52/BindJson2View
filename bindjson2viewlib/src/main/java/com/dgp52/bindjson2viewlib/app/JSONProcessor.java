package com.dgp52.bindjson2viewlib.app;

import android.view.View;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.FileManager;
import com.dgp52.bindjson2viewlib.util.Keyword;
import com.dgp52.bindjson2viewlib.util.NetworkDownloader;
import com.dgp52.bindjson2viewlib.wrappers.LockWrapper;

import java.net.URL;

public class JSONProcessor {
    private URL url;
    private String jsonString;
    private String processType;

    public JSONProcessor(URL url) {
        this.url = url;
        this.processType = Keyword.USENETWORK;
    }

    public JSONProcessor(String jsonString) {
        this.jsonString = jsonString;
        this.processType = Keyword.USELOCAL;
    }

    public void start() {
        new Thread(()-> {
            Thread.currentThread().setName(Keyword.NETWORK_THREAD);
            try {
                LockWrapper.getLock().lock();
                FileManager.createFile(Keyword.FILE_NAME, GlobalApplication.getAppContext());
                if(processType.equals(Keyword.USENETWORK) && url!=null)
                    jsonString = NetworkDownloader.tryDownload(url);
                ViewProcessor.indexingComplete = IndexJson.Index(jsonString);
                LockWrapper.getDownloadCondition().signalAll();
                if(ViewProcessor.indexingComplete)
                    FileManager.writeContent(jsonString,Keyword.FILE_NAME,GlobalApplication.getAppContext());
            } catch (Exception e) {
                ServiceException.logE(e);
            } finally {
                LockWrapper.getLock().unlock();
            }
        }).start();
    }
}