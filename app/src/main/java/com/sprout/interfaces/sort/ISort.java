package com.sprout.interfaces.sort;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;

public interface ISort {
    interface View extends IBaseView {
        void getCatalogTabReturn(CatalogTabBean result);
        void getCatalogReturn(CatalogBean result);
    }


    interface Presenter extends IBasePresenter<View> {
        void getCatalogTab(int id);
        void getCatalogList(int id);
    }


    interface Model extends IModel {
        void getCatalogTab(int id, Callback callback);
        void getCatalogList(int id,Callback callback);
    }
}
