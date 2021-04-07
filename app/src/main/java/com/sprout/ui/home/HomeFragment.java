package com.sprout.ui.home;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.HomeBean;
import com.sprout.presenter.home.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;

    List<HomeBean.DataBean.BannerBean> banners;
    BannerAdapter bannerAdapter;

    List<HomeBean.DataBean.ChannelBean> channels;
    ChannelAdapter channelAdapter;

    //品牌标题
    String brandTitle="";
    TitleAdapter titleAdapter;

    List<HomeBean.DataBean.BrandListBean> brands;
    BrandAdapter brandAdapter;




    public static HomeFragment getInstance(){
        return new HomeFragment();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        virtualLayoutManager = new VirtualLayoutManager(mContext);
        recyclerView.setLayoutManager(virtualLayoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);

        //banner
        banners = new ArrayList<>();
        bannerAdapter = new BannerAdapter(banners);
        delegateAdapter.addAdapter(bannerAdapter);

        channels = new ArrayList<>();
        channelAdapter = new ChannelAdapter(mContext,channels);
        delegateAdapter.addAdapter(channelAdapter);

        titleAdapter = new TitleAdapter(mContext,brandTitle);
        delegateAdapter.addAdapter(titleAdapter);

        brands = new ArrayList<>();
        brandAdapter = new BrandAdapter(mContext,brands);
        delegateAdapter.addAdapter(brandAdapter);

    }

    @Override
    public IHome.Presenter createPersenter() {
        return new HomePresenter();
    }

    @Override
    public void initData() {
        presenter.getHome();
    }

    @Override
    public void getHomeReturn(HomeBean result) {
        if(result.getData() != null){
            banners.clear();
            banners.addAll(result.getData().getBanner());
            bannerAdapter.notifyDataSetChanged();

            channels.clear();
            channels.addAll(result.getData().getChannel());
            channelAdapter.notifyDataSetChanged();

            //brand 品牌
            brandTitle = "品牌制造商直供";
            titleAdapter.notifyDataSetChanged();

            brands.clear();
            brands.addAll(result.getData().getBrandList());
            brandAdapter.notifyDataSetChanged();

        }
    }
}
