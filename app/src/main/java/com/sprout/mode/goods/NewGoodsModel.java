package com.sprout.mode.goods;

import com.sprout.base.BaseModel;
import com.sprout.interfaces.Callback;
import com.sprout.interfaces.goods.INewGoods;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.net.CommonSubscriber;
import com.sprout.net.HttpManager;
import com.sprout.utils.RxUtils;

import java.util.Map;

public class NewGoodsModel extends BaseModel implements INewGoods.Model {
    @Override
    public void getNewGoods(Map<String, String> map, Callback<NewGoodsBean> callback) {
        addDisposable(HttpManager.getInstance().getService().getNewGoods(map).
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<NewGoodsBean>(callback){

                    @Override
                    public void onNext(NewGoodsBean newGoodsBean) {
                        callback.success(newGoodsBean);
                    }
                }));
    }

    @Override
    public void getNewGoodsTop(Callback callback) {
        addDisposable(HttpManager.getInstance().getService().getNewGoodsTop().
                compose(RxUtils.rxScheduler()).
                subscribeWith(new CommonSubscriber<NewGoodTopBean>(callback){

                    @Override
                    public void onNext(NewGoodTopBean newGoodsBean) {
                        callback.success(newGoodsBean);
                    }
                }));
    }
}
