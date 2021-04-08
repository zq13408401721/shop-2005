package com.sprout;

import android.content.Intent;

import com.sprout.base.BaseActivity;
import com.sprout.interfaces.start.IStart;

/**
 *启动页
 */
public class StartActivity extends BaseActivity<IStart.Presenter> implements IStart.View {

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected IStart.Presenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}