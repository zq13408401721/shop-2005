package com.sprout.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sprout.app.MyApp;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageLoader {


    /**
     * 封装图片加载的方法 有图/无图
     * @param url
     * @param img
     */
    public static void imageLoad(String url, ImageView img){
        boolean bool = SpUtils.getInstance().getBoolean("imageloader");
        if(bool){
            Glide.with(img).load(url).into(img);
        }
    }


    /**
     * 圆形图片的加载
     * @param url
     * @param img
     * @param options
     */
    public static void imageLoad(String url, ImageView img, RequestOptions options){
        boolean bool = SpUtils.getInstance().getBoolean("imageloader");
        if(bool){
            Glide.with(img).load(url).apply(options).into(img);
        }
    }


    /**
     * 图片的压缩
     * @param path
     * @param width
     * @param height
     * @return
     */
    public static Bitmap scaleBitmap(String path,int width,int height){
        if(path == null || path.isEmpty()) return null;
        if(!new File(path).exists()) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        // 如果需要输出的大小比原图大，直接返回原图
        if(options.outWidth < width && options.outHeight < height){
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(path,options);
        }
        int swidth = options.outWidth;
        int sheight = options.outHeight;
        int size = 1;
        while(swidth/size > width || sheight/size > height){
            size *= 2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = size;
        Bitmap bmp = BitmapFactory.decodeFile(path,options);
        return bmp;
    }

    /**
     * bitmap转字节数组
     * @param bitmap
     * @return
     */
    public static byte[] getBytesByBitmap(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        return data;
    }
}
