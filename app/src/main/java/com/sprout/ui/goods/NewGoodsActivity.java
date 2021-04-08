package com.sprout.ui.goods;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.sprout.R;
import com.sprout.app.Delegate;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.goods.INewGoods;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.presenter.goods.NewGoodsPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class NewGoodsActivity extends BaseActivity<INewGoods.Presenter> implements INewGoods.View {

    @BindView(R.id.recy_newgoods)
    RecyclerView recyNewGoods;

    VirtualLayoutManager virtualLayoutManager;
    RecyclerView.RecycledViewPool viewPool;
    DelegateAdapter delegateAdapter;

    NewGoodTopBean newGoodTopBean;
    NewGoodsTopAdapter newGoodsTopAdapter;

    NewGoodsBean newGoodsBean;
    NewGoodsFilterAdapter newGoodsFilterAdapter;




    @Override
    protected int getLayout() {
        return R.layout.activity_newgoods;
    }

    @Override
    protected void initView() {
        virtualLayoutManager = new VirtualLayoutManager(this);
        recyNewGoods.setLayoutManager(virtualLayoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyNewGoods.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyNewGoods.setAdapter(delegateAdapter);

        newGoodsTopAdapter = new NewGoodsTopAdapter(this,null);
        delegateAdapter.addAdapter(newGoodsTopAdapter);

        newGoodsFilterAdapter = new NewGoodsFilterAdapter(this,newGoodsBean, Delegate.OBJECT);
        delegateAdapter.addAdapter(newGoodsFilterAdapter);

    }

    @Override
    protected INewGoods.Presenter createPersenter() {
        return new NewGoodsPresenter();
    }

    @Override
    protected void initData() {
        presenter.getNewGoodsTop();
        //默认的列表
        Map<String,String> map = new HashMap<>();
        map.put("isNew","1");
        map.put("page","1");
        map.put("size","10");
        map.put("order","desc");
        map.put("sort","default");
        map.put("categoryId","0");
        presenter.getNewGoods(map);
    }

    /**
     * 新品热门商品顶部数据返回
     * @param result
     */
    @Override
    public void getNewGoodsTopReturn(NewGoodTopBean result) {
        newGoodTopBean = result;
        if(result.getData() != null){
            newGoodsTopAdapter.setData(newGoodTopBean.getData().getBannerInfo());
        }
    }

    @Override
    public void getNewGoodsReturn(NewGoodsBean result) {
        newGoodsBean = result;
        newGoodsFilterAdapter.setData(newGoodsBean);
        newGoodsFilterAdapter.notifyDataSetChanged();
    }
}
