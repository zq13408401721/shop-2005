package com.sprout.mode.pay;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.pay.IPay;
import com.sprout.mode.data.AlipayBean;
import com.sprout.mode.data.LoginBean;
import com.sprout.mode.data.WXOrderBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

public class PayModel extends BaseModel implements IPay.Model {
    @Override
    public void getWxOrder(int paytype, Callback<WXOrderBean> callback) {
        addDisposable(HttpManager.getInstance().getPayService().wxOrder(paytype).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<WXOrderBean>(callback){

                    @Override
                    public void onNext(WXOrderBean wxOrderBean) {
                        callback.success(wxOrderBean);
                    }
                }));
    }

    @Override
    public void getAlipayOrder(int paytype, Callback<AlipayBean> callback) {
        addDisposable(HttpManager.getInstance().getPayService().alipayOrder(paytype).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<AlipayBean>(callback){

                    @Override
                    public void onNext(AlipayBean alipayBean) {
                        callback.success(alipayBean);
                    }
                }));
    }
}
