package com.dgp52.bindjson2view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dgp52.bindjson2viewlib.BindJson2View;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        printLog();
    }

    private void test() {
        BindJson2View.getInstance(getApplicationContext()).useNetwork("https://www.google.com").start();
    }

    private void printLog() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    ServiceException.printLog("log-cat");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
