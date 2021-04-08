package com.sprout.interfaces.goods;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.interfaces.home.IHome;
import com.sprout.mode.data.HomeBean;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.mode.data.NewGoodsBean;

import java.util.Map;

public interface INewGoods {
    interface View extends IBaseView {

        void getNewGoodsTopReturn(NewGoodTopBean result);

        void getNewGoodsReturn(NewGoodsBean result);
    }


    interface Presenter extends IBasePresenter<INewGoods.View> {
        void getNewGoodsTop();

        void getNewGoods(Map<String,String> map);
    }


    interface Model extends IModel {
        void getNewGoods(Map<String,String> map,Callback<NewGoodsBean> callback);

        void getNewGoodsTop(Callback callback);
    }
}
