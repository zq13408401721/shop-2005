package com.sprout.interfaces.login;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.LoginBean;
import com.sprout.mode.data.WxTokenBean;
import com.sprout.mode.data.WxTokenRefreshBean;

import java.util.Map;

import retrofit2.Response;

public interface IWx {
    interface View extends IBaseView {
        //定义一个接口给Presenter调用
        void getWxTokenReturn(WxTokenBean result);
        //微信token刷新
        void getWxRefreshTokenReturn(WxTokenRefreshBean result);
    }


    interface Presenter extends IBasePresenter<View> {
        //定义一个给View层调接口
        void getWxToken(Map<String,String> map);

        void getWxRefreshToken(Map<String,String> map);
    }


    interface Model extends IModel {
        //定义一个给Presenter调用的接口，用来加载数据
        void getWxToken(Map<String,String> map, Callback<WxTokenBean> callback);

        void getWxRefreshToken(Map<String,String> map,Callback<WxTokenRefreshBean> callback);
    }
}
