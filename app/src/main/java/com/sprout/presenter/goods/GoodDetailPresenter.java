package com.sprout.presenter.goods;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.goods.IGood;
import com.sprout.mode.data.AddCarBean;
import com.sprout.mode.data.CarBean;
import com.sprout.mode.data.GoodDetailBean;
import com.sprout.mode.goods.GoodDetailModel;

public class GoodDetailPresenter extends BasePresenter<IGood.View> implements IGood.Presenter {

    IGood.Model model;
    public GoodDetailPresenter(){
        model = new GoodDetailModel();
    }

    @Override
    public void getGoodDetail(int id) {
        model.getGoodDetail(id, new Callback<GoodDetailBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(GoodDetailBean goodDetailBean) {
                if(mView != null){
                    mView.getGoodDetailReturn(goodDetailBean);
                }
            }
        });
    }

    @Override
    public void getCar() {
        model.getCar(new Callback<CarBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(CarBean carBean) {
                if(mView != null){
                    mView.getCarReturn(carBean);
                }
            }
        });
    }

    @Override
    public void addCar(int goodid, int number, int pid) {
        model.addCar(goodid,number,pid,new Callback<AddCarBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(AddCarBean addCarBean) {
                if(mView != null){
                    mView.addCarReturn(addCarBean);
                }
            }
        });
    }
}
