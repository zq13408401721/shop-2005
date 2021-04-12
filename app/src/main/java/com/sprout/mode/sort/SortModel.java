package com.sprout.mode.sort;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.sort.ISort;
import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.mode.data.HomeBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

public class SortModel extends BaseModel implements ISort.Model {
    @Override
    public void getCatalogTab(int id, Callback callback) {
        addDisposable(HttpManager.getInstance().getService().getCatalogTab(id).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<CatalogTabBean>(callback){

                    @Override
                    public void onNext(CatalogTabBean catalogTabBean) {
                        callback.success(catalogTabBean);
                    }
                }));
    }

    @Override
    public void getCatalogList(int id, Callback callback) {
        addDisposable(HttpManager.getInstance().getService().getCatalog(id).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<CatalogBean>(callback){

                    @Override
                    public void onNext(CatalogBean catalogBean) {
                        callback.success(catalogBean);
                    }
                }));
    }
}
