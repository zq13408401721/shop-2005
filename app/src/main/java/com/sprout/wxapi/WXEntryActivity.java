package com.sprout.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.login.IWx;
import com.sprout.mode.data.WxTokenBean;
import com.sprout.mode.data.WxTokenRefreshBean;
import com.sprout.presenter.login.WxLoginPresenter;
import com.sprout.utils.ActivityTask;
import com.sprout.utils.SpUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;


public class WXEntryActivity extends BaseActivity<IWx.Presenter> implements IWXAPIEventHandler, IWx.View {

    IWXAPI iwxapi; //微信接口的定义

    String code;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent,this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("TAG",baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.i("TAG",baseResp.toString());
        if(baseResp instanceof SendAuth.Resp){
            SendAuth.Resp resp = (SendAuth.Resp) baseResp;
            code = resp.code;
            //resp.code;
            //resp.openId;

        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wxentry;
    }

    @Override
    protected void initView() {
        iwxapi = WXAPIFactory.createWXAPI(this, Constants.WX_APPID);
        iwxapi.registerApp(Constants.WX_APPID);
        Intent intent = getIntent();
        try {
            if(intent == null) intent = new Intent();
            iwxapi.handleIntent(intent,this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected IWx.Presenter createPersenter() {
        return new WxLoginPresenter();
    }

    @Override
    protected void initData() {
        Map<String,String> map = new HashMap<>();
        map.put("appid",Constants.WX_APPID);
        map.put("secret",Constants.WX_SECRET);
        map.put("code",code);
        map.put("grant_type","authorization_code");
        presenter.getWxToken(map);
    }

    @Override
    public void getWxTokenReturn(WxTokenBean result) {
        if(result != null){
            SpUtils.getInstance().setValue("access_token",result.getAccess_token());
            SpUtils.getInstance().setValue("expires_in",result.getExpires_in());
            SpUtils.getInstance().setValue("refresh_token",result.getRefresh_token());
            SpUtils.getInstance().setValue("openid",result.getOpenid());
            SpUtils.getInstance().setValue("unionid",result.getUnionid());
            //回到个人信息的页面
            ActivityTask.gotoMainActivity(Constants.PAGE_WXLOGIN_CODE);

        }
    }

    @Override
    public void getWxRefreshTokenReturn(WxTokenRefreshBean result) {

    }
}
