package com.sprout.presenter.login;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.login.ILogin;
import com.sprout.mode.data.LoginBean;
import com.sprout.mode.login.LoginModel;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter {

    ILogin.Model model;
    public LoginPresenter(){
        model = new LoginModel();
    }

    @Override
    public void login(String username, String pw) {
        model.login(username, pw, new Callback<LoginBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(LoginBean loginBean) {
                if(mView != null){
                    mView.loginReturn(loginBean);
                }
            }
        });
    }
}
