package com.sprout.mode.login;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.login.IWx;
import com.sprout.mode.data.LoginBean;
import com.sprout.mode.data.WxTokenBean;
import com.sprout.mode.data.WxTokenRefreshBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

import java.util.Map;

import retrofit2.Response;

public class WxLoginModel extends BaseModel implements IWx.Model {
    @Override
    public void getWxToken(Map<String, String> map, Callback<WxTokenBean> callback) {
        addDisposable(HttpManager.getInstance().getWXService().getWxAccessToken(map).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<WxTokenBean>(callback){

                    @Override
                    public void onNext(WxTokenBean wxTokenBean) {
                        callback.success(wxTokenBean);
                    }
                }));
    }

    @Override
    public void getWxRefreshToken(Map<String, String> map, Callback<WxTokenRefreshBean> callback) {
        addDisposable(HttpManager.getInstance().getWXService().getWxRefreshToken(map).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<WxTokenRefreshBean>(callback){

                    @Override
                    public void onNext(WxTokenRefreshBean wxTokenRefreshBean) {
                        callback.success(wxTokenRefreshBean);
                    }
                }));
    }
}
