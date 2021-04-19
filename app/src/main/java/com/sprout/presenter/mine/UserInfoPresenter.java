package com.sprout.presenter.mine;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.mine.IMine;
import com.sprout.mode.data.UpdateUserInfoBean;
import com.sprout.mode.mine.UserInfoModel;

import java.util.Map;

public class UserInfoPresenter extends BasePresenter<IMine.UserView> implements IMine.UserPresenter {

    IMine.UserModel model;
    public UserInfoPresenter(){
        model = new UserInfoModel();
    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
        model.updateUserInfo(map, new Callback<UpdateUserInfoBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(UpdateUserInfoBean userInfoBean) {
                if(mView != null){
                    mView.updateUserInfoReturn(userInfoBean);
                }
            }
        });
    }
}
