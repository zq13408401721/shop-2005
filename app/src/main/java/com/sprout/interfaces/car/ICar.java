package com.sprout.interfaces.car;

import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;

public interface ICar {
    interface View extends IBaseView {


    }


    interface Presenter extends IBasePresenter<View> {

    }


    interface Model extends IModel {
    }
}
