package com.sprout.mode.home;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.ChannelBean;
import com.sprout.mode.data.HomeBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel implements IHome.Model {


    @Override
    public void getHome(Callback<HomeBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getHome().
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<HomeBean>(callback){

                    @Override
                    public void onNext(HomeBean homeBean) {
                        callback.success(homeBean);
                    }
                }));
    }
}
