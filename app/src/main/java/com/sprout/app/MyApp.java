package com.sprout.app;

import android.app.Application;

import com.sprout.R;
import com.sprout.utils.SpUtils;
import com.umeng.commonsdk.UMConfigure;

public class MyApp extends Application {

    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initUM();
        initRes();
        SpUtils.getInstance().setValue("imageloader",true);
    }

    private void initUM() {
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");

        // 微信设置
        //PlatformConfig.setWeixin("wxdc1e388c3822c80b","3baf1193c85774b3fd9d18447d76cab0");
    }

    /**
     * 初始化资源
     */
    private void initRes(){
        Constants.price_word = app.getResources().getString(R.string.price_word);
    }
}
