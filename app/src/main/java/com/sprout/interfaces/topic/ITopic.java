package com.sprout.interfaces.topic;

import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;

public interface ITopic {
    interface View extends IBaseView {

    }


    interface Presenter extends IBasePresenter<View> {

    }


    interface Model extends IModel {
    }
}
