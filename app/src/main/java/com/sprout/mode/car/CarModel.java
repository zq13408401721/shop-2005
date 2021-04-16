package com.sprout.mode.car;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.car.ICar;
import com.sprout.mode.data.AddCarBean;
import com.sprout.mode.data.CarBean;
import com.sprout.mode.data.DeleteCarBean;
import com.sprout.mode.data.UpdateCarBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

import java.util.Map;

import okhttp3.RequestBody;

public class CarModel extends BaseModel implements ICar.Model  {
    @Override
    public void getCarList(Callback<CarBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getCarList().
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<CarBean>(callback){

                    @Override
                    public void onNext(CarBean carBean) {
                        callback.success(carBean);
                    }
                }));
    }

    @Override
    public void addCarJson(RequestBody body, Callback<CarBean> callback) {

    }

    @Override
    public void updateCar(Map<String, String> map, Callback<UpdateCarBean> callback) {
        addDisposable(HttpManager.getInstance().getService().updateCar(map).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<UpdateCarBean>(callback){

                    @Override
                    public void onNext(UpdateCarBean updateCarBean) {
                        callback.success(updateCarBean);
                    }
                }));
    }

    @Override
    public void deleteCar(String ids, Callback<DeleteCarBean> callback) {
        addDisposable(HttpManager.getInstance().getService().deleteCar(ids).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<DeleteCarBean>(callback){

                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                }));
    }
}
