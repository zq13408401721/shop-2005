package com.sprout.mode.login;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.login.ILogin;
import com.sprout.mode.data.HomeBean;
import com.sprout.mode.data.LoginBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {
    @Override
    public void login(String username,String pw,Callback<LoginBean> callback) {
        addDisposable(HttpManager.getInstance().getService().login(username,pw).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<LoginBean>(callback){

                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }
}
