package com.sprout.ui.mine;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.car.ICar;
import com.sprout.interfaces.mine.IMine;
import com.sprout.presenter.car.CarPresenter;
import com.sprout.presenter.mine.MinePresenter;
import com.sprout.ui.setting.SettingActivity;

import butterknife.BindView;

public class MineFragment extends BaseFragment<IMine.Presenter> implements IMine.View, View.OnClickListener {


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
        txtSetting.setOnClickListener(this);
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
        }
    }
}
