package com.sprout.ui.sort;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.presenter.sort.SortPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;

public class SortFragment extends BaseFragment<ISort.Presenter> implements ISort.View {

    @BindView(R.id.tabLayout)
    VerticalTabLayout tabLayout;
    @BindView(R.id.img_banner)
    ImageView imgBanner;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<String> tabs = new ArrayList<>();


    public static SortFragment getInstance(){
        return new SortFragment();
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    public void initView() {
        tabLayout.setTabAdapter(new TabAdapter() {
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
                        .setContent(tabs.get(position))//设置数据   也有设置字体颜色的方法
                        .build();
                return title;
            }

            @Override
            public int getBackground(int position) {
                return Color.RED;//选中的背景颜色
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
        presenter.getCatalogTab(0);
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
                tabs.add(item.getName());
            }

        }
    }

    @Override
    public void getCatalogReturn(CatalogBean result) {

    }
}
