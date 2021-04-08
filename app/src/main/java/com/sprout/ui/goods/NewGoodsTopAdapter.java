package com.sprout.ui.goods;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;

public class NewGoodsTopAdapter extends BaseDelegateAdapter<NewGoodTopBean.DataBean.BannerInfoBean> {

    public NewGoodsTopAdapter(Context context, NewGoodTopBean.DataBean.BannerInfoBean data) {
        super(context, data, Delegate.OBJECT);
    }

    public void setData(NewGoodTopBean.DataBean.BannerInfoBean data){
        super.data = data;
        notifyDataSetChanged();
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_newgoods_top;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, NewGoodTopBean.DataBean.BannerInfoBean data) {
        ImageView imgTop = (ImageView) holder.getViewById(R.id.img_top);
        TextView txtTop = (TextView) holder.getViewById(R.id.txt_top);
        ImageLoader.imageLoad(data.getImg_url(),imgTop);
        TextViewUtils.setTextView(data.getName(),txtTop);
    }

}
