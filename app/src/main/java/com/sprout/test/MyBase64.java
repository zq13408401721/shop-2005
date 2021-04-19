package com.sprout.test;


import android.util.Base64;
import android.util.Log;

public class MyBase64 {

    public static void main(String[] args) {
        String str = "ABC";
        String base64str = Base64.encodeToString(str.getBytes(),Base64.DEFAULT);
        Log.i("TA",base64str);
    }
}
