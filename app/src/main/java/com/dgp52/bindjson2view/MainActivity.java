package com.dgp52.bindjson2view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgp52.bindjson2viewlib.BindJson2View;
import com.dgp52.bindjson2viewlib.logexception.LogException;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.AttributeProcessor;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button2);
        test();
        //int id = getApplicationContext().getResources().getIdentifier("ic_launcher_round","mipmap","com.dgp52.bindjson2view");
        //button.setBackgroundResource(id);
        button.setText("test");
    }


    private void test() {
        AttributeProcessor.addAttribute(button);

        BindJson2View.getInstance(getApplicationContext())
                .useNetwork("https://dl.dropboxusercontent.com/s/4601ge88oa0mg0u/document.json?dl=0")
                .start();
        //AttributeProcessor.addAttribute(new Button(getApplicationContext()),"mybtn");
    }

    public void log(View view) {
        String log = "";
        for(LogException e : ServiceException.getLog()) {
            log = log + e.toString();
        }
        textView.setText(log);
    }
}
