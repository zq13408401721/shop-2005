package com.sprout.interfaces.mine;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.UpdateUserInfoBean;
import com.sprout.mode.data.WxUserInfoBean;

import java.util.Map;

public interface IMine {
    interface View extends IBaseView {

        void getWxUserInfoReturn(WxUserInfoBean result);
    }


    interface Presenter extends IBasePresenter<View> {
        void getWxUserInfo(Map<String,String> map);
    }


    interface Model extends IModel {
        void getWxUserInfo(Map<String,String> map,Callback<WxUserInfoBean> callback);
    }

    interface UserView extends IBaseView {
        void updateUserInfoReturn(UpdateUserInfoBean result);
    }


    interface UserPresenter extends IBasePresenter<UserView> {

        void updateUserInfo(Map<String,String> map);
    }


    interface UserModel extends IModel {
        void updateUserInfo(Map<String,String> map, Callback<UpdateUserInfoBean> callback);
    }

}
