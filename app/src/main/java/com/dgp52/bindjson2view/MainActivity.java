package com.dgp52.bindjson2view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgp52.bindjson2viewlib.processor.ViewProcessor;
import com.dgp52.bindjson2viewlib.app.BindJson2View;
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
        //btn0.setBackgroundDrawable(Asset.getDrawableFromAssets("d.jpg"));
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        test();
    }


    private void test() {
        ViewProcessor.addView(btn0);
        ViewProcessor.addView(btn1);
        ViewProcessor.addView(btn2);
       BindJson2View.INSTANCE
                .useNetwork("https://dl.dropboxusercontent.com/s/4601ge88oa0mg0u/document.json?dl=0")
                .start();
//        BindJson2View.INSTANCE
//              .useLocal("{\"binders\":[{\"tags\":[\"log2\"],\"methods\":[{\"name\":\"setBackgroundResource\",\"params\":[\"int\"],\"values\":[\"gravity\",\"mipmap\"],\"converts\":[\"image\"],\"unit\":\"resource\"}]},{\"tags\":[\"log0\"],\"methods\":[{\"name\":\"setBackgroundColor\",\"params\":[\"int\"],\"values\":[\"4286f4\"],\"converts\":[\"color\"],\"unit\":\"hex\"}]},{\"tags\":[\"log2\"],\"methods\":[{\"name\":\"setLayoutParams\",\"params\":[\"layoutParams\"],\"values\":[\"60\",\"30\"],\"converts\":[\"layoutParamsWidthHeight\"],\"unit\":\"percentage\",\"switch\":\"on\"},{\"name\":\"setText\",\"params\":[\"charSequence\"],\"values\":[\"Position\"],\"converts\":[\"string\"],\"switch\":\"off\"}]},{\"tags\":[\"log2432\"],\"methods\":[{\"name\":\"setVisibility\",\"params\":[\"int\"],\"values\":[\"0\"],\"converts\":[\"int\"]},{\"name\":\"setWidth\",\"params\":[\"int\"],\"values\":[\"50\"],\"converts\":[\"width\"],\"unit\":\"percentage\"}]},{\"tags\":[\"log4234\"],\"methods\":[{\"name\":\"setLayoutParams\",\"params\":[\"layoutParams\"],\"values\":[\"500\",\"500\"],\"converts\":[\"layoutParamsWidthHeight\"],\"unit\":\"pixel\"}]}]}")
//              .start();
    }

    public void clear(View view) {
        ServiceException.clearLogs();
    }

    public void log(View view) {
        String log = "";
        for(LogException e : ServiceException.getLog()) {
            log = log + e.toString();
        }
        textView.setText(log);
    }
}
