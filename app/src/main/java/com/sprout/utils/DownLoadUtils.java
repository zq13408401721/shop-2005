package com.sprout.utils;

import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownLoadUtils {
    private static DownLoadUtils instance;
    private OkHttpClient okHttpClient;
    private final static byte[] gSyncCode = new byte[0];

    public static DownLoadUtils get(){
        if(instance == null){
            synchronized (DownLoadUtils.class){
                if(instance == null){
                    instance = new DownLoadUtils();
                }
            }
        }
        return instance;
    }
    private DownLoadUtils(){
        okHttpClient = new OkHttpClient();
    }

    /**
     * 文件下载
     * @param url
     * @param savePath
     * @param downloadListener
     */
    public void download(final String url, final String savePath, final DownloadListener downloadListener){
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                downloadListener.onDownloadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                /*String saveFolder = isExistDir(saveDir);
                String tempPath = saveFolder + File.separator + "temp_" + getNameFromUrl(url);
                String savePath = saveFolder + File.separator + getNameFromUrl(url);*/
                try {
                    //FileUtils.deleteFile(tempPath);
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        downloadListener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    downloadListener.onDownloadSuccess(savePath);
                } catch (Exception e) {
                    downloadListener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    @NonNull
    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
    /**
     * delete file or directory
     * <ul>
     * <li>if path is null or empty, return true</li>
     * <li>if path not exist, return true</li>
     * <li>if path exist, delete recursion. return true</li>
     * <ul>
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {

        synchronized (gSyncCode) {

            File file = new File(path);
            if (!file.exists()) {
                return true;
            }
            if (file.isFile()) {
                return file.delete();
            }
            if (!file.isDirectory()) {
                return false;
            }
            File[] filesList = file.listFiles();

            if (filesList != null) {
                for (File f : filesList) {
                    if (f.isFile()) {
                        f.delete();
                    } else if (f.isDirectory()) {
                        deleteFile(f.getAbsolutePath());
                    }
                }
            }

            return file.delete();
        }

    }

    /**
     * @Method: fileRename
     * @Description: 将文件从fromName命名为toName，由于使用的是File自带的renameTo()接口，需要注意： <li>读写存储器权限</li> <li>
     * fromName和toName这两个路径在相同的挂载点。如果不在同一挂载点，重命名失败。</li>
     * @param fromName 需要重命名的文件，为文件绝对路径
     * @param toName 要改成的名字，为文件绝对路径
     * @return boolean 成功或失败
     */
    public static boolean fileRename(String fromName, String toName) {
        synchronized (gSyncCode) {
            // TODO: 根据文件名判断是否属于同一挂载点
            File fromFile = new File(fromName);
            File toFile = new File(toName);
            if (!fromFile.exists()) {
                return false;
            }
            boolean result = fromFile.renameTo(toFile);
            if (result) {
            }
            return result;
        }

    }
    public interface DownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess(String path);

        /**
         * @param progress
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }

}
