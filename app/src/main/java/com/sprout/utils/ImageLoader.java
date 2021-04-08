package com.sprout.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sprout.app.MyApp;

public class ImageLoader {

    /**
     * 封装图片加载的方法 有图/无图
     * @param url
     * @param img
     */
    public static void imageLoad(String url, ImageView img){
        boolean bool = SpUtils.getInstance().getBoolean("imageloader");
        if(bool){
            Glide.with(MyApp.app).load(url).into(img);
        }
    }

}
