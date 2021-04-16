package com.sprout.ui.pay;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.pay.IPay;
import com.sprout.mode.data.WXOrderBean;
import com.sprout.presenter.pay.PayPresenter;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;

public class PayActivity extends BaseActivity<IPay.Presenter> implements IPay.View, IWXAPIEventHandler {

    @BindView(R.id.txt_wxpay)
    TextView txtWxPay;

    IWXAPI iwxapi;

    @Override
    protected int getLayout() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        iwxapi = WXAPIFactory.createWXAPI(this, Constants.WX_APPID);
        iwxapi.registerApp(Constants.WX_APPID);

        txtWxPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getWxOrder(1);
            }
        });
    }

    @Override
    protected IPay.Presenter createPersenter() {
        return new PayPresenter();
    }

    @Override
    protected void initData() {

    }

    /**
     * 获取微信支付的订单信息
     * @param result
     */
    @Override
    public void getWxOrderReturn(WXOrderBean result) {
        if(result.getCode() == 200){
            //调起微信支付页面
            PayReq payReq = new PayReq();
            payReq.appId = Constants.WX_APPID;
            payReq.partnerId = Constants.WX_PARTNERID;
            payReq.nonceStr = result.getData().getNoncestr();
            payReq.packageValue = "Sign=WXPay";
            payReq.prepayId = result.getData().getPrepay_id();
            payReq.timeStamp = result.getData().getTimestamp();
            payReq.sign = result.getData().getSign();
            iwxapi.sendReq(payReq);
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("TAG",baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.i("TAG",baseResp.toString());
    }
}
