package com.sprout.presenter.home;

import android.widget.Toast;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.HomeBean;
import com.sprout.mode.home.HomeModel;

import java.sql.Time;

public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter {

    IHome.Model model;

    public HomePresenter(){
        model = new HomeModel();
    }

    /**
     * 获取主页数据的接口
     */
    @Override
    public void getHome() {
        model.getHome(new Callback<HomeBean>() {
            @Override
            public void fail(String msg) {
                if(mView != null){
                    mView.showToast(msg, Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void success(HomeBean homeBean) {
                if(mView != null){
                    mView.getHomeReturn(homeBean);
                }
            }
        });
    }
}
