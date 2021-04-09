package com.sprout.ui.goods;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.widget.FilterWindow;

import java.util.HashMap;
import java.util.Map;

public class NewGoodsFilterAdapter extends BaseDelegateAdapter<NewGoodsBean> implements View.OnClickListener {

    TextView txtAll;
    ConstraintLayout layoutPrice;
    TextView txtPrice;
    ImageView arrowUp;
    ImageView arrowDown;
    TextView txtFilter;
    int select=1;

    GoodFilter filter; //接口实列

    //条件筛选弹框
    FilterWindow filterWindow;

    public NewGoodsFilterAdapter(Context context, NewGoodsBean data, Delegate delegate) {
        super(context, data, delegate);
    }

    public void addGoodFilter(GoodFilter filter){
        this.filter = filter;
    }

    public void setData(NewGoodsBean data){
        super.data = data;
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_newgoods_filter;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, NewGoodsBean data) {
        txtAll = (TextView) holder.getViewById(R.id.txt_all);
        layoutPrice = (ConstraintLayout) holder.getViewById(R.id.layout_price);
        txtPrice = (TextView) holder.getViewById(R.id.txt_price);
        arrowUp = (ImageView) holder.getViewById(R.id.img_up);
        arrowDown = (ImageView) holder.getViewById(R.id.img_down);
        txtFilter= (TextView) holder.getViewById(R.id.txt_filter);

        txtAll.setOnClickListener(this);
        layoutPrice.setOnClickListener(this);
        txtFilter.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        reset();
        switch (v.getId()){
            case R.id.txt_all:
                txtAll.setTextColor(Color.RED);
                if(select != 1){
                    //刷新
                    select = 1;
                    setFileter("desc","default",0);
                }
                break;
            case R.id.layout_price:
                txtPrice.setTextColor(Color.RED);
                if(select != 2){
                    select = 2;
                    txtPrice.setTag("down");
                    arrowUp.setImageResource(R.mipmap.ic_arrow_down_nor);
                    arrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
                    setFileter("desc","price",0);
                }else{
                    //重复点击
                    String tag = (String) txtPrice.getTag();
                    if(tag.equals("down")){
                        txtPrice.setTag("up");
                        arrowUp.setImageResource(R.mipmap.ic_arrow_down_select);
                        arrowDown.setImageResource(R.mipmap.ic_arrow_down_nor);
                        setFileter("asc","price",0);
                    }else{
                        txtPrice.setTag("down");
                        arrowUp.setImageResource(R.mipmap.ic_arrow_down_nor);
                        arrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
                        setFileter("desc","price",0);
                    }
                }
                break;
            case R.id.txt_filter:
                txtFilter.setTextColor(Color.RED);
                if(select != 3){
                    select = 3;
                    filterWindow = new FilterWindow(context,data.getData().getFilterCategory());
                    filterWindow.addOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int filterId = (int) v.getTag();
                            setFileter("desc","category",filterId);
                        }
                    });
                    filterWindow.showAsDropDown(txtFilter);
                }else{
                    if(filterWindow != null){
                        if(filterWindow.isShowing()){
                            filterWindow.dismiss();
                        }else{
                            filterWindow.showAsDropDown(txtFilter);
                        }
                    }
                }
                break;

        }
    }

    /**
     * 筛选数据的条件
     * @param order
     * @param categoryId
     */
    private void setFileter(String order,String sort,int categoryId){
        //默认全选的情况的参数
        Map<String,String> map = new HashMap<>();
        map.put("isNew","1");
        map.put("page","1");
        map.put("size","10");
        map.put("order",order);
        map.put("sort",sort);
        map.put("categoryId",String.valueOf(categoryId));
        if(filter != null){
            filter.setFilterParam(map);
        }
    }

    /**
     * 重置效果
     */
    private void reset(){
        txtAll.setTextColor(Color.parseColor("#000000"));
        txtPrice.setTextColor(Color.parseColor("#000000"));
        txtFilter.setTextColor(Color.parseColor("#000000"));
        arrowUp.setImageResource(R.mipmap.ic_arrow_down_nor);
        arrowDown.setImageResource(R.mipmap.ic_arrow_down_nor);
    }


    /**
     * 接口的定义
     */
    public interface GoodFilter{
        void setFilterParam(Map<String,String> map);
    }


}
