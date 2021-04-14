package com.sprout.ui.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.HomeBean;
import com.sprout.presenter.home.HomePresenter;
import com.sprout.ui.goods.GoodDetailActivity;
import com.sprout.ui.goods.NewGoodsActivity;

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
    String brandTitle="品牌制造商直供";
    TitleAdapter titleAdapter;

    List<HomeBean.DataBean.BrandListBean> brands;
    BrandAdapter brandAdapter;

    //新品
    String newGoodTitle = "周一周四 · 新品首发";
    TitleAdapter newGoodTitleAdapter;

    List<HomeBean.DataBean.NewGoodsListBean> newGoods;
    NewGoodAdapter newGoodAdapter;

    //热门商品
    String hotTitle = "热门";
    TitleAdapter hotTitleAdapter;

    List<HomeBean.DataBean.HotGoodsListBean> hotGoods;
    HotAdapter hotAdapter;



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

        //新品
        newGoodTitleAdapter = new TitleAdapter(mContext,newGoodTitle);
        delegateAdapter.addAdapter(newGoodTitleAdapter);

        //新品标题点击
        newGoodTitleAdapter.addOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewGoodsActivity.class);
                startActivity(intent);
            }
        });

        newGoods = new ArrayList<>();
        newGoodAdapter = new NewGoodAdapter(mContext,newGoods);
        delegateAdapter.addAdapter(newGoodAdapter);

        newGoodAdapter.addOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                HomeBean.DataBean.NewGoodsListBean bean = newGoods.get(pos);
                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                intent.putExtra("goodid",bean.getId());
                startActivityForResult(intent, Constants.PAGE_REQEST_CODE_GOODDETAIL);
            }
        });

        //热门
        hotTitleAdapter = new TitleAdapter(mContext,hotTitle);
        delegateAdapter.addAdapter(hotTitleAdapter);

        hotGoods = new ArrayList<>();
        hotAdapter = new HotAdapter(mContext,hotGoods);
        delegateAdapter.addAdapter(hotAdapter);



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
            brands.clear();
            brands.addAll(result.getData().getBrandList());
            brandAdapter.notifyDataSetChanged();

            newGoods.clear();
            newGoods.addAll(result.getData().getNewGoodsList());
            newGoodAdapter.notifyDataSetChanged();

            hotGoods.clear();
            hotGoods.addAll(result.getData().getHotGoodsList());
            hotAdapter.notifyDataSetChanged();

        }
    }
}
