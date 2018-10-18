package com.dgp52.bindjson2viewlib.util;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class NetworkDownloader {

    public static String tryDownload(URL url) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = null;
        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            stringBuffer = new StringBuffer();
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
        } catch (Exception e) {
            ServiceException.logE(e);
        } finally {
            if(httpURLConnection!=null)
                httpURLConnection.disconnect();
            if(bufferedReader!=null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    ServiceException.logE(e);
                }
            }
            if(stringBuffer!=null) {
                ServiceException.logI("Content successfully downloaded");
                return stringBuffer.toString();
            }
        }
        return null;
    }
}
