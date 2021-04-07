package com.sprout.ui;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sprout.R;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.ChannelBean;
import com.sprout.mode.data.HomeBean;
import com.sprout.presenter.home.HomePresenter;
import com.sprout.ui.home.HomeFragment;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<IHome.Presenter> implements IHome.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;

    Fragment homeFragment;
    Fragment topicFragment;
    Fragment sortFragment;
    Fragment carFragment;
    Fragment mineFragment;


    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        initFragments();
        virtualLayoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(virtualLayoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);
    }

    private void initFragments(){
        homeFragment = HomeFragment.getInstance();
    }


    @Override
    protected IHome.Presenter createPersenter() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        presenter.getHome();
    }


    @Override
    public void getHomeReturn(HomeBean result) {

    }
}
