package com.sprout.ui.mine;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.car.ICar;
import com.sprout.interfaces.mine.IMine;
import com.sprout.presenter.car.CarPresenter;
import com.sprout.presenter.mine.MinePresenter;

public class MineFragment extends BaseFragment<IMine.Presenter> implements IMine.View {

    public static MineFragment getInstance(){
        return new MineFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {

    }

    @Override
    public IMine.Presenter createPersenter() {
        return new MinePresenter();
    }

    @Override
    public void initData() {

    }

}
