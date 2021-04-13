package com.sprout.ui.sort;

import android.content.Intent;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.sprout.R;
import com.sprout.base.BaseActivity;
import com.sprout.base.BaseAdapter;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.data.CategoryListBean;
import com.sprout.mode.data.CategoryTopBean;
import com.sprout.presenter.sort.SortDetailPresenter;
import com.sprout.ui.goods.GoodDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SortDetailActivity extends BaseActivity<ISort.DetailPresenter> implements ISort.DetailView {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<CategoryTopBean.DataBean.BrotherCategoryBean> topList;

    int page=1,size=10;

    int currentPos;

    List<CategoryListBean.DataBeanX.GoodsListBean> goodList;
    SortDetailAdapter sortDetailAdapter;

    TabLayout.OnTabSelectedListener tabSelectedListener;

    @Override
    protected int getLayout() {
        return R.layout.activity_sortdetail;
    }

    @Override
    protected void initView() {

        goodList = new ArrayList<>();
        sortDetailAdapter = new SortDetailAdapter(this,goodList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(sortDetailAdapter);

        topList = new ArrayList<>();

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPos = tab.getPosition();
                presenter.getCategoryList(topList.get(currentPos).getId(),page,size);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };


        sortDetailAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
               int goodid = goodList.get(pos).getId();
                Intent intent = new Intent(SortDetailActivity.this, GoodDetailActivity.class);
                intent.putExtra("goodid",goodid);
                startActivity(intent);
            }
        });
    }

    @Override
    protected ISort.DetailPresenter createPersenter() {
        return new SortDetailPresenter();
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id",0);
        currentPos = getIntent().getIntExtra("pos",0);
        //获取顶部的数据
        presenter.getCategoryTop(id);
    }


    @Override
    public void getCategoryTopReturn(CategoryTopBean result) {
        if(result.getData().getBrotherCategory() != null){
            topList.addAll(result.getData().getBrotherCategory());
            initTabLayout();
        }
    }

    /**
     * 列表数据
     * @param result
     */
    @Override
    public void getCategoryListReturn(CategoryListBean result) {
        if(result.getData() != null){
            goodList.clear();
            goodList.addAll(result.getData().getGoodsList());
            sortDetailAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout(){
        for(CategoryTopBean.DataBean.BrotherCategoryBean item:topList){
            tabLayout.addTab(tabLayout.newTab().setText(item.getName()));
        }
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        if(currentPos < topList.size()){
           tabLayout.getTabAt(currentPos).select();
        }

    }
}
