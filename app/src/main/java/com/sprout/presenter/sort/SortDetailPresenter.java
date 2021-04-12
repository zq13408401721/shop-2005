package com.sprout.presenter.sort;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.data.CategoryListBean;
import com.sprout.mode.data.CategoryTopBean;
import com.sprout.mode.sort.SortDetailModel;

public class SortDetailPresenter extends BasePresenter<ISort.DetailView> implements ISort.DetailPresenter {

    SortDetailModel model;

    public SortDetailPresenter(){
        model = new SortDetailModel();
    }

    @Override
    public void getCategoryTop(int id) {
        model.getCategoryTop(id, new Callback<CategoryTopBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(CategoryTopBean categoryTopBean) {
                if(mView!=null){
                    mView.getCategoryTopReturn(categoryTopBean);
                }
            }
        });
    }

    @Override
    public void getCategoryList(int id, int page, int size) {
        model.getCategoryList(id, page, size, new Callback<CategoryListBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(CategoryListBean categoryListBean) {
                if(mView!=null){
                    mView.getCategoryListReturn(categoryListBean);
                }
            }
        });
    }
}
