package com.sprout.ui.sort;

import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.sprout.R;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.data.CategoryListBean;
import com.sprout.mode.data.CategoryTopBean;
import com.sprout.presenter.sort.SortDetailPresenter;

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


    List<CategoryListBean.DataBeanX.GoodsListBean> goodList;
    SortDetailAdapter sortDetailAdapter;

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

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
        presenter.getCategoryList(topList.get(0).getId(),page,size);
    }
}
