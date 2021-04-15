package com.sprout.interfaces.car;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.CarBean;

public interface ICar {
    interface View extends IBaseView {

        void getCarListReturn(CarBean carBean);
    }


    interface Presenter extends IBasePresenter<View> {
        void getCarList();
    }


    interface Model extends IModel {
        void getCarList(Callback<CarBean> callback);
    }
}
