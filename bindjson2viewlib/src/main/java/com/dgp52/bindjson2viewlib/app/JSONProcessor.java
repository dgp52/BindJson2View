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
        this.processType = Keyword.App.USENETWORK.getValue();
    }

    public JSONProcessor(String jsonString) {
        this.jsonString = jsonString;
        this.processType = Keyword.App.USELOCAL.getValue();
    }

    public void start() {
        new Thread(()-> {
            Thread.currentThread().setName(Keyword.App.JSONPROCESSORTHREAD.getValue());
            try {
                LockWrapper.getLock().lock();
                FileManager.createFile(Keyword.App.FILENAME.getValue(), GlobalApplication.getAppContext());
                if(processType.equals(Keyword.App.USENETWORK.getValue()) && url!=null) {
                    jsonString = NetworkDownloader.tryDownload(url);
                    if(jsonString == null) {
                        jsonString = FileManager.readContent(Keyword.App.FILENAME.getValue(), GlobalApplication.getAppContext());
                        ServiceException.logI("JSON processor used local file. Please check your Internet connection.");
                    }
                }
                ViewProcessor.indexingComplete = IndexJson.Index(jsonString);
                if(!ViewProcessor.indexingComplete) {
                    jsonString = FileManager.readContent(Keyword.App.FILENAME.getValue(), GlobalApplication.getAppContext());
                    ViewProcessor.indexingComplete = IndexJson.Index(jsonString);
                    ServiceException.logI("JSON processor used local file. Please validate JSON.");
                }
                LockWrapper.getDownloadCondition().signalAll();
                if(ViewProcessor.indexingComplete)
                    FileManager.writeContent(jsonString,Keyword.App.FILENAME.getValue(),GlobalApplication.getAppContext());
            } catch (Exception e) {
                ServiceException.logE(e);
            } finally {
                LockWrapper.getLock().unlock();
            }
        }).start();
    }
}