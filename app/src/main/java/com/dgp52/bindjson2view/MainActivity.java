package com.dgp52.bindjson2view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dgp52.bindjson2viewlib.BindJson2View;
import com.dgp52.bindjson2viewlib.logexception.LogException;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    private void test() {
        BindJson2View.getInstance(getApplicationContext()).useNetwork("https://www.google.com").start();
    }

    public void log(View view) {
        textView = findViewById(R.id.textView);
        String log = "";
        for(LogException e : ServiceException.getLog()) {
            log = log + e.toString();
        }
        textView.setText(log);
    }
}
