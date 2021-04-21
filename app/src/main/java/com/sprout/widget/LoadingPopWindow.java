package com.sprout.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.sprout.R;

public class LoadingPopWindow extends PopupWindow {

    Context context;
    View view;

    ProgressBar progressBar;
    TextView txtLoading;

    public LoadingPopWindow(Context context){
        this.context = context;
        initView();
    }

    private void initView(){
        view = LayoutInflater.from(context).inflate(R.layout.popwindow_loading,null,false);
        setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击()
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 实例化一个ColorDrawable颜色为半透明
        /*ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);*/
        //this.backgroundAlpha(context, 0.5f);//0.0-1.0

        progressBar = view.findViewById(R.id.loadingBar);
        txtLoading = view.findViewById(R.id.txt_loading);
    }

    /**
     * 显示进度条
     * @param progress
     */
    public void setProgress(int progress){
        if(progressBar != null){
            progressBar.setProgress(progress);
            txtLoading.setText(progress+"%");
        }

    }

}
