package com.sprout.interfaces.sort;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.mode.data.CategoryListBean;
import com.sprout.mode.data.CategoryTopBean;

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

    /**
     * 分类详情页的接口
     */
    interface DetailView extends IBaseView{
        void getCategoryTopReturn(CategoryTopBean result);
        void getCategoryListReturn(CategoryListBean result);
    }

    interface DetailPresenter extends IBasePresenter<DetailView>{
        void getCategoryTop(int id);
        void getCategoryList(int id,int page,int size);
    }

    interface DetailModel extends IModel{
        void getCategoryTop(int id,Callback<CategoryTopBean> callback);
        void getCategoryList(int id,int page,int size,Callback<CategoryListBean> callback);
    }


}
