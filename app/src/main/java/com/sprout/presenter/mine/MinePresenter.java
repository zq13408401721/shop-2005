package com.sprout.presenter.mine;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.mine.IMine;
import com.sprout.mode.data.WxUserInfoBean;
import com.sprout.mode.mine.MineModel;

import java.util.Map;

public class MinePresenter extends BasePresenter<IMine.View> implements IMine.Presenter {

    IMine.Model model;
    public MinePresenter(){
        model = new MineModel();
    }

    @Override
    public void getWxUserInfo(Map<String, String> map) {
        model.getWxUserInfo(map, new Callback<WxUserInfoBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(WxUserInfoBean wxUserInfoBean) {
                if(mView != null){
                    mView.getWxUserInfoReturn(wxUserInfoBean);
                }
            }
        });
    }
}
