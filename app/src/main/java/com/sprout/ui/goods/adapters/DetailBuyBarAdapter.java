package com.sprout.ui.goods.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.CarBean;
import com.sprout.utils.SpUtils;

public class DetailBuyBarAdapter extends BaseDelegateAdapter<CarBean> {

    int currentGoodId;

    public DetailBuyBarAdapter(Context context, CarBean data) {
        super(context, data,Delegate.OBJECT);
    }

    public void setCurrentGoodId(int goodId){
        currentGoodId = goodId;
    }
    @Override
    protected LayoutHelper getLayoutHelper() {
        return new FixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT,0,0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_buy_bottom_bar;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, CarBean data) {
        //TextViewUtils.setTextView(String.valueOf(data.getData().getCartTotal().getGoodsCount()),(TextView) holder.getViewById(R.id.txt_number));
        boolean bool = SpUtils.getInstance().getBoolean(String.valueOf(currentGoodId));
        int rid = bool ? R.mipmap.ic_collect : R.mipmap.ic_uncollect;
        ImageView imgCollect  = (ImageView) holder.getViewById(R.id.img_collect);
        imgCollect.setImageResource(rid);

        ConstraintLayout layoutBuy = (ConstraintLayout) holder.getViewById(R.id.layout_buy);
        TextView txtBuy = (TextView) holder.getViewById(R.id.txt_buy);
        TextView txtCar = (TextView) holder.getViewById(R.id.txt_car);

        layoutBuy.setTag(1);
        txtBuy.setTag(2);
        txtCar.setTag(3);
        layoutBuy.setOnClickListener(itemListener);




    }
}
