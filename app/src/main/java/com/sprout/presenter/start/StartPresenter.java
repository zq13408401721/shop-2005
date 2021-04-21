package com.sprout.presenter.start;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.login.ILogin;
import com.sprout.interfaces.start.IStart;
import com.sprout.mode.data.LoginBean;
import com.sprout.mode.data.VersionBean;
import com.sprout.mode.login.LoginModel;
import com.sprout.mode.start.StartModel;

public class StartPresenter extends BasePresenter<IStart.View> implements IStart.Presenter {

    IStart.Model model;
    public StartPresenter(){
        model = new StartModel();
    }

    @Override
    public void getVersion() {
        model.getVersion(new Callback<VersionBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(VersionBean versionBean) {
                if(mView != null){
                    mView.getVersionReturn(versionBean);
                }
            }
        });
    }
}
