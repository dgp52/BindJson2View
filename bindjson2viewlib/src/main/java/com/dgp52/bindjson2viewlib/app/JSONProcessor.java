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
            Thread.currentThread().setName(Keyword.JSON_PROCESSOR_THREAD);
            try {
                LockWrapper.getLock().lock();
                FileManager.createFile(Keyword.FILE_NAME, GlobalApplication.getAppContext());
                if(processType.equals(Keyword.USENETWORK) && url!=null) {
                    jsonString = NetworkDownloader.tryDownload(url);
                    if(jsonString == null) {
                        jsonString = FileManager.readContent(Keyword.FILE_NAME, GlobalApplication.getAppContext());
                        ServiceException.logI("JSON processor used local file. Please check your Internet connection.");
                    }
                }
                ViewProcessor.indexingComplete = IndexJson.Index(jsonString);
                if(!ViewProcessor.indexingComplete) {
                    jsonString = FileManager.readContent(Keyword.FILE_NAME, GlobalApplication.getAppContext());
                    ViewProcessor.indexingComplete = IndexJson.Index(jsonString);
                    ServiceException.logI("JSON processor used local file. Please validate JSON.");
                }
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