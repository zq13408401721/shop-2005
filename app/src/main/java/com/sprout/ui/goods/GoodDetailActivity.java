package com.sprout.ui.goods;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sprout.R;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.goods.IGood;
import com.sprout.mode.data.GoodDetailBean;
import com.sprout.presenter.goods.GoodDetailPresenter;
import com.sprout.ui.goods.adapters.DetailInfoAdapter;
import com.sprout.ui.goods.adapters.DetailWebAdapter;

import butterknife.BindView;

public class GoodDetailActivity extends BaseActivity<IGood.Presenter> implements IGood.View {

    @BindView(R.id.recy_detail)
    RecyclerView recyDetail;

    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;

    GoodDetailBean.DataBeanX.InfoBean infoBean;
    DetailInfoAdapter detailInfoAdapter;
    //网页内容
    DetailWebAdapter detailWebAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_good_detail;
    }

    @Override
    protected void initView() {
        virtualLayoutManager = new VirtualLayoutManager(this);
        recyDetail.setLayoutManager(virtualLayoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyDetail.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyDetail.setAdapter(delegateAdapter);

        detailInfoAdapter = new DetailInfoAdapter(this,infoBean);
        delegateAdapter.addAdapter(detailInfoAdapter);
        detailInfoAdapter.addEventListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();
            }
        });

        detailWebAdapter = new DetailWebAdapter(this,infoBean);
        delegateAdapter.addAdapter(detailWebAdapter);
    }

    @Override
    protected IGood.Presenter createPersenter() {
        return new GoodDetailPresenter();
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("goodid",0);
        if(id <= 0){

        }else{
            presenter.getGoodDetail(id);
        }
    }

    @Override
    public void getGoodDetailReturn(GoodDetailBean result) {
        if(result.getData() != null){
            infoBean = result.getData().getInfo();
            detailInfoAdapter.refreshData(infoBean);
            detailInfoAdapter.notifyDataSetChanged();
            detailWebAdapter.refreshData(infoBean);
            detailWebAdapter.notifyDataSetChanged();
        }
    }
}
