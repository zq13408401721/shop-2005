package com.sprout.ui.goods.adapters;

import android.content.Context;

import com.alibaba.android.vlayout.LayoutHelper;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.GoodDetailBean;

public class DetailBannerAdapter extends BaseDelegateAdapter<GoodDetailBean.DataBeanX.GalleryBean> {
    public DetailBannerAdapter(Context context, GoodDetailBean.DataBeanX.GalleryBean data) {
        super(context, data, Delegate.OBJECT);
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_gooddetail_banner;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, GoodDetailBean.DataBeanX.GalleryBean data) {

    }
}
