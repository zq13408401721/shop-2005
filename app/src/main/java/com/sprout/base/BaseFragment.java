package com.sprout.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.sprout.interfaces.IBasePresenter;
import com.sprout.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    protected P presenter;
    Unbinder unbinder;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(getLayout(),null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        unbinder = ButterKnife.bind(this,view);
        presenter = createPersenter();
        if(presenter != null){
            presenter.attachView(this);
        }
        initView();
        initData();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract P createPersenter();

    public abstract void initData();

    @Override
    public void tips(String tip) {

    }

    @Override
    public void loading(int visible) {

    }

    @Override
    public void showToast(String msg,int time){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
        if(presenter != null){
            presenter.unAttachView();
        }
    }
}
