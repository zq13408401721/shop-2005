package com.sprout.presenter.car;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.car.ICar;
import com.sprout.mode.car.CarModel;
import com.sprout.mode.data.CarBean;
import com.sprout.mode.data.DeleteCarBean;
import com.sprout.mode.data.UpdateCarBean;

import java.util.Map;

import okhttp3.RequestBody;

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

    @Override
    public void addCarJson(RequestBody body) {
        model.addCarJson(body,new Callback<CarBean>() {
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

    @Override
    public void udpateCar(Map<String, String> map) {
        model.updateCar(map,new Callback<UpdateCarBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(UpdateCarBean result) {
                if(mView != null){
                    mView.updateCarReturn(result);
                }
            }
        });
    }

    @Override
    public void deleteCar(String ids) {
        model.deleteCar(ids,new Callback<DeleteCarBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(DeleteCarBean result) {
                if(mView != null){
                    mView.deleteCarReturn(result);
                }
            }
        });
    }
}
