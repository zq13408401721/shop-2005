package com.sprout.app;

import android.app.Application;

import com.sprout.R;
import com.sprout.utils.SpUtils;

public class MyApp extends Application {

    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initRes();
    }

    /**
     * 初始化资源
     */
    private void initRes(){
        Constants.price_word = app.getResources().getString(R.string.price_word);
    }
}
