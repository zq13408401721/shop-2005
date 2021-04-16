package com.sprout.ui.pay;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alipay.sdk.app.PayTask;
import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.pay.IPay;
import com.sprout.mode.data.AlipayBean;
import com.sprout.mode.data.WXOrderBean;
import com.sprout.presenter.pay.PayPresenter;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.lang.ref.WeakReference;
import java.util.Map;

import butterknife.BindView;

public class PayActivity extends BaseActivity<IPay.Presenter> implements IPay.View, View.OnClickListener {

    @BindView(R.id.txt_wxpay)
    TextView txtWxPay;
    @BindView(R.id.txt_alipay)
    TextView txtAlipay;

    IWXAPI iwxapi;
    Object obj;

    MyHandler myHandler = new MyHandler(this);

    @Override
    protected int getLayout() {
        obj = new Object();
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        iwxapi = WXAPIFactory.createWXAPI(this, Constants.WX_APPID);
        iwxapi.registerApp(Constants.WX_APPID);

        txtWxPay.setOnClickListener(this);
        txtAlipay.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_wxpay:
                presenter.getWxOrder(1);
                break;
            case R.id.txt_alipay:
                presenter.getAlipayOrder(1);
                break;
        }
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

    /**
     * 获取支付宝订单返回
     * @param result
     */
    @Override
    public void getAlipayOrderReturn(AlipayBean result) {
        if(result.getCode() == 200){
            String info = result.getData();  //订单信息
            alipay(info);
        }
    }

    private void alipay(String info){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(PayActivity.this);
                Map<String,String> map = payTask.payV2(info,true);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = map;
                myHandler.sendMessage(msg);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void updateAlipayResult(Map<String,String> map){
        String status = map.get("resultStatus");
        if(status.equals("9000")){//成功
            Toast.makeText(this, map.get("memo"), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, map.get("memo"), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * handler定义
     */
    class MyHandler extends Handler{

        WeakReference<PayActivity> weakReference;

        public MyHandler(PayActivity activity){
            weakReference = new WeakReference<PayActivity>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.i("TAG",msg.toString());
            weakReference.get().updateAlipayResult((Map<String, String>) msg.obj);
        }
    }

}
