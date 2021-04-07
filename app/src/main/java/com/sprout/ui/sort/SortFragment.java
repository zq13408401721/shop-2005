package com.sprout.ui.sort;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.car.ICar;
import com.sprout.interfaces.sort.ISort;
import com.sprout.presenter.car.CarPresenter;
import com.sprout.presenter.sort.SortPresenter;
import com.sprout.ui.car.CarFragment;

public class SortFragment extends BaseFragment<ISort.Presenter> implements ISort.View {
    public static SortFragment getInstance(){
        return new SortFragment();
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    public void initView() {

    }

    @Override
    public ISort.Presenter createPersenter() {
        return new SortPresenter();
    }

    @Override
    public void initData() {

    }

}
