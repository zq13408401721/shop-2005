package com.sprout.ui.home;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.HomeBean;
import com.sprout.presenter.home.HomePresenter;

public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View {

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public IHome.Presenter createPersenter() {
        return new HomePresenter();
    }

    @Override
    public void initData() {

    }

    @Override
    public void getHomeReturn(HomeBean result) {

    }
}
