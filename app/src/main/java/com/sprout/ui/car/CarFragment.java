package com.sprout.ui.car;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.car.ICar;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.CarBean;
import com.sprout.mode.data.HomeBean;
import com.sprout.presenter.car.CarPresenter;
import com.sprout.presenter.home.HomePresenter;
import com.sprout.ui.mine.MineFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CarFragment extends BaseFragment<ICar.Presenter> implements ICar.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.checkbox_all)
    CheckBox checkBoxAll;
    @BindView(R.id.txt_totalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.txt_submit)
    TextView txtSubmit;

    boolean isEdit=false;

    int total=0;
    float totalPrice=0f;

    List<CarBean.DataBean.CartListBean> list;
    CarListAdapter carListAdapter;

    public static CarFragment getInstance(){
        return new CarFragment();
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_car;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        carListAdapter = new CarListAdapter(mContext,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(carListAdapter);

        checkBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bool = checkBoxAll.isChecked();
                updateDataSelect(bool);
                updateTotal();
                carListAdapter.notifyDataSetChanged();
            }
        });

        /**
         * 列表选中状态的变化监听
         */
        carListAdapter.addCheckBoxChange(new CarListAdapter.ICheckBoxChange() {
            @Override
            public void update(CarBean.DataBean.CartListBean data, boolean select) {
                countSelect();
                updateTotal();
            }
        });
        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = txtEdit.getText().toString();
                if(str.equals("编辑")){
                    txtEdit.setText("完成");
                    isEdit = true;
                    txtSubmit.setText("删除所选");
                    updateDataSelect(false);
                }else{
                    txtEdit.setText("编辑");
                    isEdit = false;
                    txtSubmit.setText("下单");
                    countSelect();
                }
                carListAdapter.setEdit(isEdit);
                carListAdapter.notifyDataSetChanged();
                updateEditState();
            }
        });
        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 计算当前列表中选中的商品数量和价格
     */
    private void countSelect(){
        total = 0;
        totalPrice = 0;
        boolean bool = true; //默认全选
        for (CarBean.DataBean.CartListBean item:list) {
            if(isEdit){
                if(!item.editselect){
                    if(bool){
                        bool = false;
                    }
                }else{
                    total += item.getNumber();
                }
            }else{
                if(!item.normalselect){
                    if(bool){
                        bool = false;
                    }
                }else{
                    total += item.getNumber();
                    totalPrice += item.getNumber()*item.getMarket_price();
                }
            }
        }
        checkBoxAll.setChecked(bool);
        /*checkBoxAll.setText("全选("+total+")");
        txtTotalPrice.setText("￥"+String.valueOf(totalPrice));*/
    }

    /**
     * 刷新数据条目中的选中状态
     * @param select
     */
    private void updateDataSelect(boolean select){
        total = 0;
        totalPrice = 0;
        for (CarBean.DataBean.CartListBean item:list) {
            if(isEdit){
                item.editselect = select;
            }else{
                item.normalselect = select;
            }
            if(select){
                total += item.getNumber();
                totalPrice += item.getNumber()*item.getMarket_price();
            }
        }
    }

    /**
     * 总价赋值
     */
    private void updateTotal(){
        String str = "全选("+total+")";
        checkBoxAll.setText(str);
        String strPrice = "￥"+totalPrice;
        txtTotalPrice.setText(strPrice);
    }

    /**
     * 更新编辑状态的界面
     */
    private void updateEditState(){
        if(isEdit){
            txtTotalPrice.setVisibility(View.GONE);
            updateDataSelect(false);
            checkBoxAll.setChecked(false);
            checkBoxAll.setText("全选(0)");
            carListAdapter.notifyDataSetChanged();
        }else{
            txtTotalPrice.setVisibility(View.VISIBLE);
            int[] result = countList();
            if(result[0] == 0){
                checkBoxAll.setChecked(false);
            }else{
                checkBoxAll.setChecked(true);
            }
            String str = "全选("+result[1]+")";
            checkBoxAll.setText(str);
            String strPrice = "￥"+result[2];
            txtTotalPrice.setText(strPrice);
        }
    }

    /**
     *
     * @return
     */
    private int[] countList(){
        int[] result = new int[3];
        int allselect = 1; //0非全选 1全选
        total = 0;
        totalPrice = 0;
        for (CarBean.DataBean.CartListBean item:list) {
            if(!item.normalselect){
                if(allselect == 1){
                    allselect = 0;
                }
            }
            total += item.getNumber();
            totalPrice += item.getNumber()*item.getMarket_price();
        }
        result[0] = allselect;
        result[1] = total;
        result[2] = (int) totalPrice;
        return result;
    }

    @Override
    public ICar.Presenter createPersenter() {
        return new CarPresenter();
    }

    @Override
    public void initData() {
        presenter.getCarList();
    }

    @Override
    public void getCarListReturn(CarBean carBean) {
        total = 0;
        totalPrice = 0;
        if(carBean.getData() != null){
            list.clear();
            list.addAll(carBean.getData().getCartList());
            carListAdapter.notifyDataSetChanged();
        }
    }
}
