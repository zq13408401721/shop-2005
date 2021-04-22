package com.sprout;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.bytedance.sdk.openadsdk.TTFullScreenVideoAd;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.sprout.app.Constants;
import com.sprout.app.MyApp;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.start.IStart;
import com.sprout.mode.data.VersionBean;
import com.sprout.presenter.start.StartPresenter;
import com.sprout.test.EditTextActivity;
import com.sprout.test.TouchEventActivity;
import com.sprout.ui.start.StartFragment;
import com.sprout.utils.DownLoadUtils;
import com.sprout.utils.FileUtils;
import com.sprout.utils.SpUtils;
import com.sprout.utils.SystemUtils;
import com.sprout.widget.LoadingPopWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *启动页
 */
public class StartActivity extends BaseActivity<IStart.Presenter> implements IStart.View,View.OnClickListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.img_one)
    ImageView imgOne;
    @BindView(R.id.img_two)
    ImageView imgTwo;
    @BindView(R.id.img_three)
    ImageView imgThree;
    @BindView(R.id.layout_dot)
    LinearLayout layoutDot;
    @BindView(R.id.layout_start)
    ConstraintLayout layoutStart;

    List<StartFragment> fragments;
    MyPagerAdapter myPagerAdapter;

    LoadingPopWindow loadingPopWindow;

    /************************广告相关**********************/
    TTBannerAd bannerAd;



    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
       // initAdvert();
        initFullVideo();
    }

    /**
     * 初始化
     */
    private void init(){
        boolean bool = SpUtils.getInstance().getBoolean("iscomein"); //是否进入过app
        if(!bool){
            layoutDot.setVisibility(View.GONE);
            initFragments();
            myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(myPagerAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    updateDot(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }else{
            comeInApp();
        }
    }

    private void initFragments(){
        fragments = new ArrayList<>();
        for(int i=0; i<3; i++){
            StartFragment startFragment = StartFragment.getInstance(i+1,3);
            startFragment.addOnClickListener(this);
            fragments.add(startFragment);
        }
    }


    @Override
    public void onClick(View v) {

        SpUtils.getInstance().setValue("iscomein",true);
        //进入app
        comeInApp();
    }

    private void comeInApp(){
        Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        //finish();
    }

    /**
     * 更新底部的原点显示效果
     * @param pos
     */
    private void updateDot(int pos){
        imgOne.setImageResource(R.drawable.img_dot_normal);
        imgTwo.setImageResource(R.drawable.img_dot_normal);
        imgThree.setImageResource(R.drawable.img_dot_normal);
        if(pos == 0){
            imgOne.setImageResource(R.drawable.img_dot_select);
        }else if(pos == 1){
            imgTwo.setImageResource(R.drawable.img_dot_select);
        }else if(pos == 2){
            imgThree.setImageResource(R.drawable.img_dot_select);
        }
    }

    @Override
    protected IStart.Presenter createPersenter() {
        return new StartPresenter();
    }

    @Override
    protected void initData() {
        presenter.getVersion();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void getVersionReturn(VersionBean result) {
        if(result.getCode() == 200){
            boolean bool = SystemUtils.isUpdate(MyApp.app,result.getData().getPackagename(),result.getData().getVersionCode());
            if(bool){
                //下载apk
                loadingPopWindow = new LoadingPopWindow(this);
                loadingPopWindow.showAsDropDown(layoutStart);
                String url = result.getData().getUrl();
                String filename = url.substring(url.lastIndexOf("/")+1,url.length());
                String filepath = FileUtils.checkFile(FileUtils.getLocalPath(),filename);
                if(new File(filepath).exists()){

                    installApk(filepath);
                    return;
                }
                //下载
                DownLoadUtils.get().download(url, filepath, new DownLoadUtils.DownloadListener() {
                    @Override
                    public void onDownloadSuccess(String path) {
                        //安装
                        installApk(path);
                    }

                    @Override
                    public void onDownloading(int progress) {
                        Log.i("Loading",progress+"");
                        Message msg = new Message();
                        msg.obj = progress;
                        myHander.sendMessage(msg);
                    }

                    @Override
                    public void onDownloadFailed() {
                        Toast.makeText(StartActivity.this, "下载出错", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                init();
            }
        }
    }

    /**
     * 安装apk
     */
    private void installApk(String filepath){
        //FileUtils.installApk(filepath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(filepath)), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void initAdvert(){
        TTAdNative mTTAdNative = TTAdSdk.getAdManager().createAdNative(this);
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(Constants.ADVERT_POS_ID)
                .setSupportDeepLink(true)
                .setAdCount(1)
                .setImageAcceptedSize(1080, 1920)
                .build();
        mTTAdNative.loadBannerAd(adSlot, new TTAdNative.BannerAdListener() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onBannerAdLoad(TTBannerAd ttBannerAd) {

            }
        });
    }

    private void initFullVideo(){
        TTAdNative mTTAdNative = TTAdSdk.getAdManager().createAdNative(this);
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(Constants.ADVERT_FULL_VIDEO_ID)
                .setSupportDeepLink(true)
                .setOrientation(TTAdConstant.VERTICAL)//必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
                .build();
        mTTAdNative.loadFullScreenVideoAd(adSlot, new TTAdNative.FullScreenVideoAdListener() {
            @Override
            public void onError(int i, String s) {
                Log.i("Advert",s);
            }

            @Override
            public void onFullScreenVideoAdLoad(TTFullScreenVideoAd ttFullScreenVideoAd) {
                Log.i("Advert","onFullScreenVideoAdLoad");
                ttFullScreenVideoAd.showFullScreenVideoAd(StartActivity.this);
            }

            @Override
            public void onFullScreenVideoCached() {
                Log.i("Advert","onFullScreenVideoCached");
            }
        });
    }


    private Handler myHander = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int progress = (int) msg.obj;
            loadingPopWindow.setProgress(progress);
        }
    };

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
