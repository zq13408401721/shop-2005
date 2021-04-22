package com.sprout.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.sprout.BuildConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    public static boolean isExist(String path){
        return new File(path).exists();
    }

    /**
     * 获取本地sd卡路径
     * @return
     */
    public static String getLocalPath(){
        String path = "data/data/"+BuildConfig.APPLICATION_ID;
        /*if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            path = Environment.getExternalStorageDirectory().getPath();
        }else{
            path = Environment.getStorageDirectory().getAbsolutePath();
        }*/
        return path;
    }

    /**
     * 判断当前文件是否存在
     * @param path
     * @param filename
     */
    public static String checkFile(String path,String filename){
        File file = new File(path);
        if(!file.exists()){  //目录不存，先创建目录
            file.mkdirs();
        }
        String filepath = path+File.separator+filename;
        file = new File(filepath);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return filepath;
    }


    /**
     * 静默安装
     * @param apkPath
     * @return
     */
    public static boolean installApk(String apkPath) {

        Process process = null;

        BufferedReader successResult = null;

        BufferedReader errorResult = null;

        StringBuilder successMsg = new StringBuilder();

        StringBuilder errorMsg = new StringBuilder();

        try {
//            //7.0以前版本使用
//            process = new ProcessBuilder("pm", "install", "-r", apkPath).start();
            //7.0以后版本使用

            /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                process = new ProcessBuilder("pm", "install","-i", BuildConfig.APPLICATION_ID, "-r", apkPath).start();
            }else{
                process = new ProcessBuilder("pm", "install", "-r", apkPath).start();
            }*/
            Runtime.getRuntime().exec("chmod 777 " + apkPath);

            //process = new ProcessBuilder("pm", "install", "-i",BuildConfig.APPLICATION_ID,"-r", apkPath).start();
            String command = "pm install -i com.sprout --user 0 "+apkPath;
            String[] args = {"pm", "install", "-r", apkPath};
            //process  = Runtime.getRuntime().exec(command);
            process = new ProcessBuilder(args).start();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));

            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s;

            while ((s = successResult.readLine()) != null) {

                successMsg.append(s);

            }

            while ((s = errorResult.readLine()) != null) {

                errorMsg.append(s);

            }

        } catch (Exception e) {



        } finally {

            try {

                if (successResult != null) {

                    successResult.close();

                }

                if (errorResult != null) {

                    errorResult.close();

                }

            } catch (Exception e) {



            }

            if (process != null) {

                process.destroy();

            }

        }

        Log.e("result",""+errorMsg.toString()+",successMsg="+successMsg);

//        Toast.makeText(context,"  "+successMsg , Toast.LENGTH_LONG).show();

        //如果含有“success”单词则认为安装成功

        return successMsg.toString().equalsIgnoreCase("success");

    }


    /**
     * 静默卸载
     * @param context
     * @param pkg
     */
    public static void unInstall(Context context, String pkg){

        Intent intent = new Intent();

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent sender = PendingIntent.getActivity(context, 0, intent, 0);

        PackageInstaller mPackageInstaller = context.getPackageManager().getPackageInstaller();

        mPackageInstaller.uninstall(pkg, sender.getIntentSender());// 卸载APK

    }

}
