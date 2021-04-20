package com.sprout.mode.mine;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.mine.IMine;
import com.sprout.mode.data.WxTokenRefreshBean;
import com.sprout.mode.data.WxUserInfoBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

import java.util.Map;

public class MineModel extends BaseModel implements IMine.Model {
    @Override
    public void getWxUserInfo(Map<String, String> map, Callback<WxUserInfoBean> callback) {
        addDisposable(HttpManager.getInstance().getWXService().getWxUserInfo(map).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<WxUserInfoBean>(callback){

                    @Override
                    public void onNext(WxUserInfoBean wxUserInfoBean) {
                        callback.success(wxUserInfoBean);
                    }
                }));
    }
}
