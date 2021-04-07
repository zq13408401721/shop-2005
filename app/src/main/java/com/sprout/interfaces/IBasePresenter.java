package com.sprout.interfaces;

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();


}
