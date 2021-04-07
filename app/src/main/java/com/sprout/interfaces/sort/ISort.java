package com.sprout.interfaces.sort;

import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;

public interface ISort {
    interface View extends IBaseView {


    }


    interface Presenter extends IBasePresenter<View> {

    }


    interface Model extends IModel {
    }
}
