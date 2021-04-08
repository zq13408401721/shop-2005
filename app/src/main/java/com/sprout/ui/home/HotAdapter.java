package com.sprout.ui.home;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.HomeBean;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;

import java.util.List;

public class HotAdapter extends BaseDelegateAdapter<HomeBean.DataBean.HotGoodsListBean> {

    public HotAdapter(Context context, List<HomeBean.DataBean.HotGoodsListBean> list){
        super(context,list, Delegate.LIST);
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_hot_item;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, HomeBean.DataBean.HotGoodsListBean data) {
        ImageView imgHot = (ImageView) holder.getViewById(R.id.img_hot);
        TextView txtHotTitle = (TextView) holder.getViewById(R.id.txt_hot_title);

        ImageLoader.imageLoad(data.getList_pic_url(),imgHot);
        TextViewUtils.setTextView(data.getName(),txtHotTitle);

    }

}
