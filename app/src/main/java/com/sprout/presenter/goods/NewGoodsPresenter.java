package com.sprout.presenter.goods;

import android.widget.Toast;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.goods.INewGoods;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.mode.goods.NewGoodsModel;

import java.util.Map;

public class NewGoodsPresenter extends BasePresenter<INewGoods.View> implements INewGoods.Presenter {

    INewGoods.Model model;
    public NewGoodsPresenter(){
        model = new NewGoodsModel();
    }

    @Override
    public void getNewGoodsTop() {
        model.getNewGoodsTop(new Callback<NewGoodTopBean>() {
            @Override
            public void fail(String msg) {
                if(mView != null){
                    mView.showToast(msg, Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void success(NewGoodTopBean newGoodsBean) {
                if(mView != null){
                    mView.getNewGoodsTopReturn(newGoodsBean);
                }
            }
        });
    }

    @Override
    public void getNewGoods(Map<String, String> map) {
        model.getNewGoods(map, new Callback<NewGoodsBean>() {
            @Override
            public void fail(String msg) {
                if(mView != null){
                    mView.showToast(msg, Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void success(NewGoodsBean newGoodsBean) {
                if(mView != null){
                    mView.getNewGoodsReturn(newGoodsBean);
                }
            }
        });
    }
}
