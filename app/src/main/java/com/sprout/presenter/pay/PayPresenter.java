package com.sprout.presenter.pay;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.pay.IPay;
import com.sprout.mode.data.AlipayBean;
import com.sprout.mode.data.WXOrderBean;
import com.sprout.mode.pay.PayModel;

public class PayPresenter extends BasePresenter<IPay.View> implements IPay.Presenter {

    IPay.Model model;
    public PayPresenter(){
        model = new PayModel();
    }

    @Override
    public void getWxOrder(int paytype) {
        model.getWxOrder(paytype, new Callback<WXOrderBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(WXOrderBean wxOrderBean) {
                if(mView != null){
                    mView.getWxOrderReturn(wxOrderBean);
                }
            }
        });
    }

    @Override
    public void getAlipayOrder(int paytype) {
        model.getAlipayOrder(paytype, new Callback<AlipayBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(AlipayBean alipayBean) {
                if(mView != null){
                    mView.getAlipayOrderReturn(alipayBean);
                }
            }
        });
    }
}
