package com.sprout.interfaces.car;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.CarBean;
import com.sprout.mode.data.DeleteCarBean;
import com.sprout.mode.data.UpdateCarBean;

import java.util.Map;

import okhttp3.RequestBody;

public interface ICar {
    interface View extends IBaseView {

        void getCarListReturn(CarBean carBean);

        void updateCarReturn(UpdateCarBean result);

        void deleteCarReturn(DeleteCarBean result);
    }


    interface Presenter extends IBasePresenter<View> {
        void getCarList();
        void addCarJson(RequestBody body);

        void udpateCar(Map<String,String> map);

        void deleteCar(String ids);


    }


    interface Model extends IModel {
        void getCarList(Callback<CarBean> callback);
        void addCarJson(RequestBody body,Callback<CarBean> callback);

        void updateCar(Map<String,String> map,Callback<UpdateCarBean> callback);

        void deleteCar(String ids, Callback<DeleteCarBean> callback);
    }
}
