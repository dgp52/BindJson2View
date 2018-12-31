package com.dgp52.bindjson2view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgp52.bindjson2viewlib.BindJson2View;
import com.dgp52.bindjson2viewlib.logexception.LogException;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.AttributeProcessor;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button2);
        linearLayout = findViewById(R.id.linearLayout);
        test();
    }


    private void test() {
        AttributeProcessor.addAttribute(button);

        BindJson2View.getInstance()
                .useNetwork("https://dl.dropboxusercontent.com/s/4601ge88oa0mg0u/document.json?dl=0")
                .start();
        AttributeProcessor.addAttribute(linearLayout);
        AttributeProcessor.addAttribute(textView);
    }

    public void log(View view) {
        String log = "";
        for(LogException e : ServiceException.getLog()) {
            log = log + e.toString();
        }
        textView.setText(log);
    }
}
