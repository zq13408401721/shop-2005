package com.sprout.ui.mine;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.sprout.R;
import com.sprout.app.GlideEngine;
import com.sprout.app.MyApp;
import com.sprout.app.MyAppGlideModule;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.mine.IMine;
import com.sprout.mode.data.UpdateUserInfoBean;
import com.sprout.presenter.mine.UserInfoPresenter;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.SpUtils;
import com.sprout.utils.TextViewUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class UserInfoActivity extends BaseActivity<IMine.UserPresenter> implements IMine.UserView, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_birthday)
    TextView txtBirthday;

    private final int width = 400;
    private final int height = 400;

    /*********oss 文件上传*****************/
    String bucketName = "sprout-app";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";
    String key = "LTAI5t7afAVVFZq84X6puaqa"; //appkey
    String secret = "K4t5BRAiSttIk9HNR8D1z1itxb1oiR"; //密码
    OSSClient ossClient; //ossclient对象

    @Override
    protected int getLayout() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void initView() {
        String head = SpUtils.getInstance().getString("avatar");
        String nickname = SpUtils.getInstance().getString("nickname");
        String birthday = SpUtils.getInstance().getString("birthday");
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
        ImageLoader.imageLoad(head,imgHead,options);
        TextViewUtils.setTextView(nickname,txtNickname);
        TextViewUtils.setTextView(birthday,txtBirthday);

        imgHead.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        txtNickname.setOnClickListener(this);
        txtBirthday.setOnClickListener(this);

        initOssClient();

    }

    private void initOssClient(){
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key,secret,"");
        // 配置类如果不设置，会有默认配置。
        ClientConfiguration conf = new  ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000);  // socket超时，默认15秒。*
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2);  // 失败后最大重试次数，默认2次。
        ossClient = new OSSClient(MyApp.app, ossPoint, credentialProvider);
    }

    @Override
    protected IMine.UserPresenter createPersenter() {
        return new UserInfoPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void updateUserInfoReturn(UpdateUserInfoBean result) {
        if(result.getData() != null){
            SpUtils.getInstance().setValue("avatar",result.getData().getAvatar());
            SpUtils.getInstance().setValue("nickname",result.getData().getNickname());
            SpUtils.getInstance().setValue("birthday",String.valueOf(result.getData().getBirthday()));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.img_head:
                openPhotos();
                break;
            case R.id.txt_nickname:
                break;
            case R.id.txt_birthday:
                break;
        }
    }

    /**
     * 打开相册
     */
    private void openPhotos(){
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .imageSpanCount(4)
                .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                .forResult(new OnResultCallbackListener() {
                    @Override
                    public void onResult(List result) {
                        if(result.size() > 0){
                            LocalMedia media = (LocalMedia) result.get(0);
                            String path = media.getPath();
                            parseSmallImg(path);
                        }
                    }

                    @Override
                    public void onCancel() {
                        Log.i("TAG","oncancel");
                    }
                });

    }

    private void parseSmallImg(String path){
        //二次采样
        Bitmap bitmap = ImageLoader.scaleBitmap(path,width,height);
        uploadBitmap(bitmap);
    }

    /**
     * 图片上传
     * @param bmp
     */
    private void uploadBitmap(Bitmap bmp){
        byte[] bts = ImageLoader.getBytesByBitmap(bmp);
        String uid = SpUtils.getInstance().getString("uid");
        uid = "admin";
        String filename = uid+"/"+System.currentTimeMillis()/1000+Math.random()*10000+".png";

        PutObjectRequest put = new PutObjectRequest(bucketName, filename, bts);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.i("TAG",currentSize+"/"+totalSize);
            }
        });
        /**
         * 上传头像的异步任务
         */
        OSSAsyncTask task = ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.i("TAG",result.toString());
                //获取头像上传成功的地址
                String url  = ossClient.presignPublicObjectURL(request.getBucketName(), request.getObjectKey());
                updateUserHead(url);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                Log.i("TAG",serviceException.toString());
            }
        });
    }

    /**
     * 更新新的头像上传地址
     * @param avatar
     */
    private void updateUserHead(String avatar){
        //更新服务器的头像数据
        Map<String,String> map = new HashMap<>();
        map.put("avatar",avatar);
        presenter.updateUserInfo(map);
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
        ImageLoader.imageLoad(avatar,imgHead);
        Log.i("TAG","头像刷新");
    }
}
