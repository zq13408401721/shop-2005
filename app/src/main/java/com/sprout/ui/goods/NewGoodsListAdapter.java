package com.sprout.ui.goods;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;

import java.util.List;

public class NewGoodsListAdapter extends BaseDelegateAdapter<NewGoodsBean.DataBeanX.GoodsListBean> {
    public NewGoodsListAdapter(Context context, List<NewGoodsBean.DataBeanX.GoodsListBean> data) {
        super(context, data, Delegate.LIST);
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return new GridLayoutHelper(2);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_newgoodlist_item;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, NewGoodsBean.DataBeanX.GoodsListBean data) {
        ImageView imgItem = (ImageView) holder.getViewById(R.id.img_goodlist);
        TextView txtName = (TextView)holder.getViewById(R.id.txt_name);
        TextView txtPrice = (TextView)holder.getViewById(R.id.txt_price);

        ImageLoader.imageLoad(data.getList_pic_url(),imgItem);
        TextViewUtils.setTextView(data.getName(),txtName);
        String price = Constants.price_word.replace("$",data.getRetail_price());
        TextViewUtils.setTextView(price,txtPrice);
    }
}
