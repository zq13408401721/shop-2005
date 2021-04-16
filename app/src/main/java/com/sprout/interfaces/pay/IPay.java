package com.sprout.interfaces.pay;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.interfaces.login.ILogin;
import com.sprout.mode.data.AlipayBean;
import com.sprout.mode.data.LoginBean;
import com.sprout.mode.data.WXOrderBean;

public interface IPay {
    interface View extends IBaseView {
        //定义一个接口给Presenter调用
        void getWxOrderReturn(WXOrderBean result);

        void getAlipayOrderReturn(AlipayBean result);
    }


    interface Presenter extends IBasePresenter<View> {
        //定义一个给View层调用的接口
        void getWxOrder(int paytype);

        void getAlipayOrder(int paytype);
    }


    interface Model extends IModel {
        //定义一个给Presenter调用的接口，用来加载数据
        void getWxOrder(int paytype, Callback<WXOrderBean> callback);

        void getAlipayOrder(int paytype,Callback<AlipayBean> callback);
    }
}
