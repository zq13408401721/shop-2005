package com.sprout.mode.sort;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.mode.data.CategoryListBean;
import com.sprout.mode.data.CategoryTopBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

public class SortDetailModel extends BaseModel implements ISort.DetailModel {
    @Override
    public void getCategoryTop(int id, Callback<CategoryTopBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getCategoryTop(id).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<CategoryTopBean>(callback){

                    @Override
                    public void onNext(CategoryTopBean categoryTopBean) {
                        callback.success(categoryTopBean);
                    }
                }));
    }

    @Override
    public void getCategoryList(int id, int page, int size, Callback<CategoryListBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getCategoryList(id,page,size).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<CategoryListBean>(callback){

                    @Override
                    public void onNext(CategoryListBean categoryListBean) {
                        callback.success(categoryListBean);
                    }
                }));
    }
}
