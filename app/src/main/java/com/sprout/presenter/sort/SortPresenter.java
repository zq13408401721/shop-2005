package com.sprout.presenter.sort;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.mode.sort.SortModel;

public class SortPresenter extends BasePresenter<ISort.View> implements ISort.Presenter {

    ISort.Model model;
    public SortPresenter(){
        model = new SortModel();
    }

    @Override
    public void getCatalogTab(int id) {
        model.getCatalogTab(id, new Callback<CatalogTabBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(CatalogTabBean result) {
                if(mView != null){
                    mView.getCatalogTabReturn(result);
                }
            }
        });
    }

    @Override
    public void getCatalogList(int id) {
        model.getCatalogList(id, new Callback<CatalogBean>() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(CatalogBean result) {
                if(mView != null){
                    mView.getCatalogReturn(result);
                }
            }
        });
    }
}
