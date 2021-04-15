package com.sprout.mode.car;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.car.ICar;
import com.sprout.mode.data.CarBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

public class CarModel extends BaseModel implements ICar.Model {
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
}
