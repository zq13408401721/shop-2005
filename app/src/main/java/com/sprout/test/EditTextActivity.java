package com.sprout.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sprout.R;

import java.util.HashMap;
import java.util.Map;

public class EditTextActivity extends AppCompatActivity {

    AppCompatEditText editText;
    Button btnInput;

    Map<String,String> map = new HashMap<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //接收消息
            super.handleMessage(msg);
        }
    };

    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //接收消息
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        editText = findViewById(R.id.txt_edit);
        btnInput = findViewById(R.id.btn_input);
        initView();
        map.put(null,"a");
        Message msg = new Message();
        msg.what = 1;
        //发送
        handler.sendMessage(msg);
        Message msg1 = new Message();
        msg1.what = 1;
        //发送
        handler2.sendMessage(msg1);
    }

    private void initView(){

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputWord();
            }
        });

    }

    private void inputWord(){
        SpannableString spannableString = new SpannableString("百度");
        URLSpan urlSpan = new URLSpan("www.baidu.com"){
            @Override
            public void onClick(View widget) {
                super.onClick(widget);
                Toast.makeText(EditTextActivity.this,"www.baidu.com",Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(urlSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.append(spannableString);
        editText.setMovementMethod(LinkMovementMethod.getInstance());
    }
}