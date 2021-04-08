package com.sprout.ui.home;

import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.HomeBean;

public class HotAdapter extends BaseDelegateAdapter<HomeBean.DataBean.HotGoodsListBean> {
    @Override
    protected LayoutHelper getLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_hot_item;
    }


    @Override
    protected void bindData(BaseViewHolder holder, HomeBean.DataBean.HotGoodsListBean data) {

    }
}
