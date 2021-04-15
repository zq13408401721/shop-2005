package com.sprout.presenter.car;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.car.ICar;
import com.sprout.mode.car.CarModel;
import com.sprout.mode.data.CarBean;

public class CarPresenter extends BasePresenter<ICar.View> implements ICar.Presenter {

    ICar.Model model;
    public CarPresenter(){
        model = new CarModel();
    }

    @Override
    public void getCarList() {
        model.getCarList(new Callback<CarBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(CarBean carBean) {
                if(mView != null){
                    mView.getCarListReturn(carBean);
                }
            }
        });
    }
}
