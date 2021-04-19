package com.sprout.ui.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.car.ICar;
import com.sprout.interfaces.mine.IMine;
import com.sprout.presenter.car.CarPresenter;
import com.sprout.presenter.mine.MinePresenter;
import com.sprout.ui.setting.SettingActivity;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.SpUtils;
import com.sprout.utils.TextViewUtils;

import butterknife.BindView;

public class MineFragment extends BaseFragment<IMine.Presenter> implements IMine.View, View.OnClickListener {


    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_setting)
    TextView txtSetting;

    public static MineFragment getInstance(){
        return new MineFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        String head = SpUtils.getInstance().getString("avatar");
        String nickname = SpUtils.getInstance().getString("nickname");
        RoundedCorners roundedCorners = new RoundedCorners(40);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        ImageLoader.imageLoad(head,imgAvatar,options);
        TextViewUtils.setTextView(nickname,txtNickname);

        txtSetting.setOnClickListener(this);
        imgAvatar.setOnClickListener(this);
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
        }
    }
}
