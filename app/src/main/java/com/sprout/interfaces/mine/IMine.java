package com.sprout.interfaces.mine;

import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;

public interface IMine {
    interface View extends IBaseView {


    }


    interface Presenter extends IBasePresenter<View> {

    }


    interface Model extends IModel {
    }
}
