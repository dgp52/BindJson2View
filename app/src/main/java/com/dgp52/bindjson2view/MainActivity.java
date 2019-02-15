package com.dgp52.bindjson2view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgp52.bindjson2viewlib.app.ViewProcessor;
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
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        linearLayout = findViewById(R.id.linearLayout);
        test();
    }


    private void test() {
        ViewProcessor.addView(btn0);
        ViewProcessor.addView(btn1);
        ViewProcessor.addView(btn2);
       BindJson2View.getInstance()
                .useNetwork("https://dl.dropboxusercontent.com/s/4601ge88oa0mg0u/document.json?dl=0")
                .start();
//     BindJson2View.getInstance()
//              .useLocal("{\"binders\":[{\"tags\":[\"log0\",\"log2\"],\"methods\":[{\"name\":\"setBackgroundColor\",\"params\":[\"int\"],\"values\":[\"4286f4\"],\"converts\":[\"color\"],\"unit\":\"hex\"}]},{\"tags\":[\"log0\"],\"methods\":[{\"name\":\"setLayoutParams\",\"params\":[\"layoutParams\"],\"values\":[\"500\",\"500\"],\"converts\":[\"layoutParamsWidthHeight\"],\"unit\":\"pixel\"},{\"name\":\"setText\",\"params\":[\"charSequence\"],\"values\":[\"Position\"],\"converts\":[\"string\"]}]},{\"tags\":[\"log2\"],\"methods\":[{\"name\":\"setVisibility\",\"params\":[\"int\"],\"values\":[\"0\"],\"converts\":[\"int\"]},{\"name\":\"setWidth\",\"params\":[\"int\"],\"values\":[\"50\"],\"converts\":[\"width\"],\"unit\":\"percentage\"}]},{\"tags\":[\"log4234\"],\"methods\":[{\"name\":\"setLayoutParams\",\"params\":[\"layoutParams\"],\"values\":[\"500\",\"500\"],\"converts\":[\"layoutParamsWidthHeight\"],\"unit\":\"pixel\"}]}]}")
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
