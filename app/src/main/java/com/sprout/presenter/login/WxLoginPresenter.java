package com.sprout.presenter.login;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.login.IWx;
import com.sprout.mode.data.WxTokenBean;
import com.sprout.mode.data.WxTokenRefreshBean;
import com.sprout.mode.login.WxLoginModel;

import java.util.Map;

import retrofit2.Response;

public class WxLoginPresenter extends BasePresenter<IWx.View> implements IWx.Presenter {

    IWx.Model model;
    public WxLoginPresenter(){
        model = new WxLoginModel();
    }

    @Override
    public void getWxToken(Map<String, String> map) {
        model.getWxToken(map, new Callback<WxTokenBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(WxTokenBean wxTokenBean) {
                if(mView != null){
                    mView.getWxTokenReturn(wxTokenBean);
                }
            }
        });
    }

    @Override
    public void getWxRefreshToken(Map<String, String> map) {
        model.getWxRefreshToken(map, new Callback<WxTokenRefreshBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(WxTokenRefreshBean wxTokenRefreshBean) {
                if(mView != null){
                    mView.getWxRefreshTokenReturn(wxTokenRefreshBean);
                }
            }
        });
    }
}
