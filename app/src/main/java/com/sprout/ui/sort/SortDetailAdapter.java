package com.sprout.ui.sort;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.base.BaseAdapter;
import com.sprout.mode.data.CategoryListBean;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;

import java.util.List;

public class SortDetailAdapter extends BaseAdapter<CategoryListBean.DataBeanX.GoodsListBean> {
    public SortDetailAdapter(Context context, List<CategoryListBean.DataBeanX.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_sort_detail_item;
    }

    @Override
    protected void bindData(CategoryListBean.DataBeanX.GoodsListBean data, VH vh) {

        ImageLoader.imageLoad(data.getList_pic_url(), (ImageView) vh.getViewById(R.id.img_sort));
        TextViewUtils.setTextView(data.getName(), (TextView) vh.getViewById(R.id.txt_name));
        String price = context.getResources().getString(R.string.price_word);
        price = price.replace(Constants.price_word,String.valueOf(data.getRetail_price()));
        TextViewUtils.setTextView(price, (TextView) vh.getViewById(R.id.txt_price));
    }
}
