package com.sprout.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.MotionEvent;

import com.sprout.R;
import com.sprout.utils.MyLog;

import java.util.HashMap;

public class TouchEventActivity extends AppCompatActivity {

    private MyHandler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        myHandler = new MyHandler();
        Message msg = new Message();
        myHandler.sendMessage(msg);
        HashMap map = new HashMap();
        map.put("a","11");
        map.put("a","22");
        map.put("b","33");
        //如果a的hash=b的hash  hash冲突
        //hash->链表/红黑树;  a.key = a  b.key = b
        map.get(null);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyLog.log(ev,"Activity","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MyLog.log(event,"Activity","onTouchEvent");
        return super.onTouchEvent(event);
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }
}