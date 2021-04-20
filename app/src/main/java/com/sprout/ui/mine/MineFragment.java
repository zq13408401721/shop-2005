package com.sprout.ui.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.car.ICar;
import com.sprout.interfaces.mine.IMine;
import com.sprout.mode.data.WxUserInfoBean;
import com.sprout.presenter.car.CarPresenter;
import com.sprout.presenter.mine.MinePresenter;
import com.sprout.ui.login.LoginActivity;
import com.sprout.ui.setting.SettingActivity;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.SpUtils;
import com.sprout.utils.TextViewUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MineFragment extends BaseFragment<IMine.Presenter> implements IMine.View, View.OnClickListener {


    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_setting)
    TextView txtSetting;
    @BindView(R.id.txt_loginout)
    TextView txtLoginOut;

    public static MineFragment getInstance(){
        return new MineFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        String uid = SpUtils.getInstance().getString("uid");
        if(uid == null ||  uid.isEmpty()){
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            return;
        }
        String head = SpUtils.getInstance().getString("avatar");
        String nickname = SpUtils.getInstance().getString("nickname");
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
        ImageLoader.imageLoad(head,imgAvatar,options);
        TextViewUtils.setTextView(nickname,txtNickname);

        txtSetting.setOnClickListener(this);
        imgAvatar.setOnClickListener(this);
        txtLoginOut.setOnClickListener(this);
    }

    /**
     * 显示微信相关的用户信息
     */
    public void wxUpdateInfo(){
        Map<String,String> map = new HashMap<>();
        String access_token = SpUtils.getInstance().getString("access_token");
        String openid = SpUtils.getInstance().getString("openid");
        map.put("access_token",access_token);
        map.put("openid",openid);
        presenter.getWxUserInfo(map);
    }


    @Override
    public IMine.Presenter createPersenter() {
        return new MinePresenter();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_setting:
                Intent intent = new Intent(mContext, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.img_avatar:
                Intent intent1 = new Intent(mContext, UserInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.txt_loginout:
                loginOut();
                break;
        }
    }

    private void loginOut(){
        SpUtils.getInstance().remove("uid");
        SpUtils.getInstance().remove("token");
        SpUtils.getInstance().remove("avatar");
    }

    @Override
    public void getWxUserInfoReturn(WxUserInfoBean result) {
        String head = result.getHeadimgurl();
        String nickname = result.getNickname();
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
        ImageLoader.imageLoad(head,imgAvatar,options);
        TextViewUtils.setTextView(nickname,txtNickname);
    }
}
