package com.sprout.mode.start;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.home.IHome;
import com.sprout.interfaces.start.IStart;
import com.sprout.mode.data.HomeBean;
import com.sprout.mode.data.VersionBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

public class StartModel extends BaseModel implements IStart.Model {


    @Override
    public void getVersion(Callback<VersionBean> callback) {
        addDisposable(HttpManager.getInstance().getVersionApi().getVersion().
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<VersionBean>(callback){

                    @Override
                    public void onNext(VersionBean versionBean) {
                        callback.success(versionBean);
                    }
                }));
    }
}
