package com.sprout.mode.mine;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.mine.IMine;
import com.sprout.mode.data.LoginBean;
import com.sprout.mode.data.UpdateUserInfoBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

import java.util.Map;

public class UserInfoModel extends BaseModel implements IMine.UserModel {

    @Override
    public void updateUserInfo(Map<String, String> map, Callback<UpdateUserInfoBean> callback) {
        addDisposable(HttpManager.getInstance().getService().updateUserInfo(map).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<UpdateUserInfoBean>(callback){

                    @Override
                    public void onNext(UpdateUserInfoBean userInfoBean) {
                        callback.success(userInfoBean);
                    }
                }));
    }
}
