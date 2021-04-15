package com.sprout.ui.car;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.sprout.R;
import com.sprout.base.BaseAdapter;
import com.sprout.mode.data.CarBean;
import com.sprout.widget.NumSelector;

import java.util.List;

public class CarListAdapter extends BaseAdapter<CarBean.DataBean.CartListBean> {

    private boolean isEdit; //是否试编辑状态

    public CarListAdapter(Context context, List<CarBean.DataBean.CartListBean> data) {
        super(context, data);
    }

    public void setEdit(boolean bool){
        isEdit = bool;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_carlist_item;
    }

    @Override
    protected void bindData(CarBean.DataBean.CartListBean data, VH vh) {
        CheckBox checkBox = (CheckBox) vh.getViewById(R.id.checkbox);
        ImageView imgGood = (ImageView) vh.getViewById(R.id.img_good);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_price);
        TextView txtNum = (TextView) vh.getViewById(R.id.txt_num);
        ConstraintLayout layoutCommon = (ConstraintLayout) vh.getViewById(R.id.layout_common);
        NumSelector numSelector = (NumSelector)vh.getViewById(R.id.numselector);

        if(isEdit){
            txtName.setVisibility(View.GONE);
            txtNum.setVisibility(View.GONE);
            layoutCommon.setVisibility(View.VISIBLE);
            numSelector.initView();
            numSelector.addNumberUpdate(new NumSelector.INumberUpdate() {
                @Override
                public void update(int num) {

                }
            });
        }else{
            txtName.setVisibility(View.VISIBLE);
            txtNum.setVisibility(View.VISIBLE);
            layoutCommon.setVisibility(View.GONE);
        }
    }
}
