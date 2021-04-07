package com.sprout.ui.car;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.car.ICar;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.HomeBean;
import com.sprout.presenter.car.CarPresenter;
import com.sprout.presenter.home.HomePresenter;
import com.sprout.ui.mine.MineFragment;

public class CarFragment extends BaseFragment<ICar.Presenter> implements ICar.View {
    public static CarFragment getInstance(){
        return new CarFragment();
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_car;
    }

    @Override
    public void initView() {

    }

    @Override
    public ICar.Presenter createPersenter() {
        return new CarPresenter();
    }

    @Override
    public void initData() {

    }

}
