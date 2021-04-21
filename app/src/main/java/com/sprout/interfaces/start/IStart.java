package com.sprout.interfaces.start;

import com.sprout.interfaces.Callback;
import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;
import com.sprout.interfaces.IModel;
import com.sprout.mode.data.VersionBean;

import okhttp3.internal.Version;

public interface IStart {

    interface View extends IBaseView {
        void getVersionReturn(VersionBean result);
    }


    interface Presenter extends IBasePresenter<View> {
        void getVersion();
    }


    interface Model extends IModel {
        void getVersion(Callback<VersionBean> callback);
    }

}
