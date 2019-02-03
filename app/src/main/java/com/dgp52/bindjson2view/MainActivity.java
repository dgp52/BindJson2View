package com.dgp52.bindjson2view;

import android.app.Service;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgp52.bindjson2viewlib.ViewProcessor;
import com.dgp52.bindjson2viewlib.BindJson2View;
import com.dgp52.bindjson2viewlib.logexception.LogException;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btn0, btn1, btn2;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        linearLayout = findViewById(R.id.linearLayout);
        test();

        //ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(100,100);
        //btn0.setLayoutParams((LinearLayout.LayoutParams)p);
        ServiceException.logI(btn0.getLayoutParams().toString());
    }


    private void test() {
        ViewProcessor.addView(btn0);
        ViewProcessor.addView(btn1);
        ViewProcessor.addView(btn2);
        BindJson2View.getInstance()
                .useNetwork("https://dl.dropboxusercontent.com/s/4601ge88oa0mg0u/document.json?dl=0")
                .start();
    }

    public void log(View view) {
        String log = "";
        for(LogException e : ServiceException.getLog()) {
            log = log + e.toString();
        }
        textView.setText(log);
    }
}
