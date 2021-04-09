package com.sprout.utils;

import android.util.Log;
import android.view.MotionEvent;

public class MyLog {

    public static void log(MotionEvent motionEvent,String name,String fun){
        String action="";
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                action = "down";
                break;
            case MotionEvent.ACTION_UP:
                action = "UP";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "move";
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "cancel";
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("action:"+action);
        sb.append("name:"+name);
        sb.append("fun:"+fun);
        Log.i("Touch",sb.toString());
    }
}
