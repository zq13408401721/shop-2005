package com.sprout.ui.sort;

import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.presenter.sort.SortPresenter;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class SortFragment extends BaseFragment<ISort.Presenter> implements ISort.View {

    @BindView(R.id.tabLayout)
    VerticalTabLayout tabLayout;
    @BindView(R.id.img_banner)
    ImageView imgBanner;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<CatalogBean.DataBean.CurrentCategoryBean.SubCategoryListBean> sortList;
    SortAdapter sortAdapter;

    List<CatalogTabBean.DataBean.CategoryListBean> tabs = new ArrayList<>();
    TabAdapter tabAdapter;


    int currentPos; //当前分类的下标

    public static SortFragment getInstance(){
        return new SortFragment();
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    public void initView() {
        sortList = new ArrayList<>();
        sortAdapter = new SortAdapter(mContext,sortList);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerView.setAdapter(sortAdapter);
        sortAdapter.addItemClick(new SortAdapter.ItemClick() {
            @Override
            public void onItemClick(int pos) {
                if(sortList.size() > pos){
                    int id = sortList.get(pos).getParent_id();
                    Intent intent = new Intent(mContext,SortDetailActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("pos",currentPos);
                    startActivity(intent);
                }
            }
        });
    }

    private void initAdapter(){
        tabAdapter = new TabAdapter() {
            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public QTabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public QTabView.TabTitle getTitle(int position) {
                QTabView.TabTitle title = new QTabView.TabTitle.Builder()
                        .setContent(tabs.get(position).getName())//设置数据   也有设置字体颜色的方法
                        .build();
                return title;
            }

            @Override
            public int getBackground(int position) {
                return Color.RED;//选中的背景颜色
            }
        };
        tabLayout.setTabAdapter(tabAdapter);
        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                currentPos = position;
                int id = tabs.get(position).getId();
                presenter.getCatalogList(id);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    @Override
    public ISort.Presenter createPersenter() {
        return new SortPresenter();
    }

    @Override
    public void initData() {

        //获取tab数据
        if(tabs.size() == 0){
            presenter.getCatalogTab(0);
        }else{
            presenter.getCatalogList(tabs.get(0).getId());
            initAdapter();
        }
    }

    /**
     * 获取tab的返回
     * @param result
     */
    @Override
    public void getCatalogTabReturn(CatalogTabBean result) {
        if(result.getData().getCategoryList() != null){
            boolean first = false;
            //获取第一个tab对应的列表
            for (CatalogTabBean.DataBean.CategoryListBean item:result.getData().getCategoryList()) {
                if(!first){
                    first = true;
                    presenter.getCatalogList(item.getId());
                }
                tabs.add(item);
            }
            if(tabAdapter == null){
                initAdapter();
            }
        }
    }

    /**
     * 右边的列表数据
     * @param result
     */
    @Override
    public void getCatalogReturn(CatalogBean result) {
        if(result.getData().getCurrentCategory() != null){
            ImageLoader.imageLoad(result.getData().getCurrentCategory().getWap_banner_url(),imgBanner);
            TextViewUtils.setTextView(result.getData().getCurrentCategory().getName()+"分类",txtTitle);
            sortList.clear();
            sortList.addAll(result.getData().getCurrentCategory().getSubCategoryList());
            sortAdapter.notifyDataSetChanged();
        }
    }
}
