package com.sprout.interfaces.goods;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.GoodDetailBean;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.mode.data.NewGoodsBean;

import java.util.Map;

public interface IGood {
    interface View extends IBaseView {

        void getGoodDetailReturn(GoodDetailBean result);
    }


    interface Presenter extends IBasePresenter<IGood.View> {
        void getGoodDetail(int id);
    }


    interface Model extends IModel {
        void getGoodDetail(int id, Callback<GoodDetailBean> callback);
    }
}
