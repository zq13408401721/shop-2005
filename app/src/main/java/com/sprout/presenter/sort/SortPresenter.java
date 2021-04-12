package com.sprout.presenter.sort;

import com.sprout.base.BasePresenter;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.sort.SortModel;

public class SortPresenter extends BasePresenter<ISort.View> implements ISort.Presenter {

    ISort.Model model;
    public SortPresenter(){
        model = new SortModel();
    }

    @Override
    public void getCatalogTab(int id) {
        model.getCatalogTab(id, new Callback() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {

            }
        });
    }

    @Override
    public void getCatalogList(int id) {
        model.getCatalogList(id, new Callback() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {

            }
        });
    }
}
