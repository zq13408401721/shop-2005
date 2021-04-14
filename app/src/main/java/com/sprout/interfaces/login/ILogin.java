package com.sprout.interfaces.login;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.HomeBean;
import com.sprout.mode.data.LoginBean;

public interface ILogin {
    interface View extends IBaseView {
        //定义一个接口给Presenter调用
        void loginReturn(LoginBean result);
    }


    interface Presenter extends IBasePresenter<ILogin.View> {
        //定义一个给View层调用的接口
        void login(String username,String pw);
    }


    interface Model extends IModel {
        //定义一个给Presenter调用的接口，用来加载数据
        void login(String username,String pw,Callback<LoginBean> callback);
    }
}
