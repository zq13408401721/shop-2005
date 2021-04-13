package com.sprout.ui.goods.adapters;

import android.content.Context;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.GoodDetailBean;
import com.sprout.utils.TextViewUtils;

public class DetailInfoAdapter extends BaseDelegateAdapter<GoodDetailBean.DataBeanX.InfoBean> {
    public DetailInfoAdapter(Context context, GoodDetailBean.DataBeanX.InfoBean data) {
        super(context, data, Delegate.OBJECT);
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_gooddetail_info;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, GoodDetailBean.DataBeanX.InfoBean data) {
        TextViewUtils.setTextView(data.getName(), (TextView) holder.getViewById(R.id.txt_name));
        TextViewUtils.setTextView(data.getGoods_brief(), (TextView) holder.getViewById(R.id.txt_desc));
        String price = context.getResources().getString(R.string.price_word);
        price = price.replace(Constants.price_word,String.valueOf(data.getRetail_price()));
        TextViewUtils.setTextView(price, (TextView) holder.getViewById(R.id.txt_price));
        ConstraintLayout layoutSelect = (ConstraintLayout) holder.getViewById(R.id.layout_select);
        layoutSelect.setTag(data.getId());
        layoutSelect.setOnClickListener(itemListener);
    }
}
