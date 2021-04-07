package com.sprout.interfaces.home;

import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;

public interface IMain {
    interface View extends IBaseView {


    }


    interface Presenter extends IBasePresenter<View> {

    }


    interface Model extends IModel {
    }
}
