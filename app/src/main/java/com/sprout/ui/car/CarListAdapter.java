package com.sprout.ui.car;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.sprout.R;
import com.sprout.base.BaseAdapter;
import com.sprout.mode.data.CarBean;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;
import com.sprout.widget.NumSelector;

import java.util.List;

public class CarListAdapter extends BaseAdapter<CarBean.DataBean.CartListBean> {

    private boolean isEdit; //是否试编辑状态

    private ICheckBoxChange checkBoxChange;
    public void addCheckBoxChange(ICheckBoxChange change){
        this.checkBoxChange = change;
    }

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

        ImageLoader.imageLoad(data.getList_pic_url(),imgGood);

        //编辑状态和正常的状态
        if(isEdit){
            txtName.setVisibility(View.GONE);
            txtNum.setVisibility(View.GONE);
            layoutCommon.setVisibility(View.VISIBLE);
            checkBox.setChecked(data.editselect);
            numSelector.initView();
            numSelector.updateNumber(data.getNumber());
            numSelector.addNumberUpdate(new NumSelector.INumberUpdate() {
                @Override
                public void update(int num) {
                    data.setNumber(num);
                    //更新服务器的商品数量
                    if(checkBoxChange != null){
                        checkBoxChange.updateCarInfo(data);
                    }
                }
            });
        }else{
            txtName.setVisibility(View.VISIBLE);
            txtNum.setVisibility(View.VISIBLE);
            //checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(data.normalselect);
            TextViewUtils.setTextView(data.getGoods_name(),txtName);
            TextViewUtils.setTextView(String.valueOf(data.getNumber()),txtNum);

            layoutCommon.setVisibility(View.GONE);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isEdit){
                    data.editselect = isChecked;
                }else{
                    data.normalselect = isChecked;
                }
                if(checkBoxChange != null){
                    checkBoxChange.update(data,isChecked);
                }
            }
        });
    }

    public interface ICheckBoxChange{
        void update(CarBean.DataBean.CartListBean data,boolean select);

        void updateCarInfo(CarBean.DataBean.CartListBean data);

    }
}
