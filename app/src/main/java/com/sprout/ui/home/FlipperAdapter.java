package com.sprout.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.interfaces.login.IWx;

import java.util.List;

public class FlipperAdapter extends BaseDelegateAdapter<List<String>> {
    public FlipperAdapter(Context context, List<String> list) {
        super(context, list, Delegate.OBJECT);
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_home_flipper;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, List<String> data) {
        ViewFlipper viewFlipper = (ViewFlipper) holder.getViewById(R.id.viewFlipper);

        for(int i=0; i<data.size(); i++){
            View view = LayoutInflater.from(context).inflate(R.layout.layout_flipper_item,null,false);
            ImageView img = view.findViewById(R.id.img);
            TextView txtTitle = view.findViewById(R.id.txt_title);
            img.setImageResource(getResId(i));
            txtTitle.setText(data.get(i));
            viewFlipper.addView(view);
        }

    }

    private int getResId(int index){
        if(index == 0) {
            return R.mipmap.ic_new;
        }else if(index == 1){
            return R.mipmap.ic_toutiao;
        }else{
            return R.mipmap.ic_tengxun;
        }
    }
}
