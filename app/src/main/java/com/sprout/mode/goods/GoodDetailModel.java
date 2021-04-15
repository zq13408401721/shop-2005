package com.sprout.mode.goods;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.goods.IGood;
import com.sprout.mode.data.AddCarBean;
import com.sprout.mode.data.CarBean;
import com.sprout.mode.data.GoodDetailBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

public class GoodDetailModel extends BaseModel implements IGood.Model {
    @Override
    public void getGoodDetail(int id, Callback<GoodDetailBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getGoodDetail(id).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<GoodDetailBean>(callback){

                    @Override
                    public void onNext(GoodDetailBean goodDetailBean) {
                        callback.success(goodDetailBean);
                    }
                }));
    }

    @Override
    public void getCar(Callback<CarBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getCarData().
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<CarBean>(callback){

                    @Override
                    public void onNext(CarBean carBean) {
                        callback.success(carBean);
                    }
                }));
    }

    @Override
    public void addCar(int goodid, int number, int pid, Callback<AddCarBean> callback) {
        addDisposable(HttpManager.getInstance().getService().addCar(goodid,number,pid).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<AddCarBean>(callback){

                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        callback.success(addCarBean);
                    }
                }));
    }
}
