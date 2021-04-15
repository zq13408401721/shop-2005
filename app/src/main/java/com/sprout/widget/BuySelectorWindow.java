package com.sprout.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sprout.R;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.utils.TextViewUtils;

import java.util.List;

public class BuySelectorWindow extends PopupWindow {

    Context context;
    View view;
    ImageView imgGood;
    NumSelector numSelector; //自定义组件

    //由创建Popupwindow的类来实现点击事件
    View.OnClickListener click;

    int number=1;


    public BuySelectorWindow(Context context){
        this.context = context;
        initView();
    }

    /**
     * 类似接口回调
     * @param onClickListener
     */
    public void addOnClickListener(View.OnClickListener onClickListener){
        click = onClickListener;
    }


    private void initView(){
        view = LayoutInflater.from(context).inflate(R.layout.layout_popwindow_buy,null,false);
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
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //this.backgroundAlpha(context, 0.5f);//0.0-1.0

        numSelector = view.findViewById(R.id.numberSelector);
        numSelector.initView();
        numSelector.addNumberUpdate(new NumSelector.INumberUpdate() {
            @Override
            public void update(int num) {
                number = num;
            }
        });
    }

    public int getNumber(){
        return number;
    }

}
