package com.sprout.ui.topic;

import com.sprout.R;
import com.sprout.base.BaseFragment;
import com.sprout.interfaces.sort.ISort;
import com.sprout.interfaces.topic.ITopic;
import com.sprout.presenter.sort.SortPresenter;
import com.sprout.presenter.topic.TopicPresenter;
import com.sprout.ui.sort.SortFragment;

public class TopicFragment extends BaseFragment<ITopic.Presenter> implements ITopic.View {
    public static TopicFragment getInstance(){
        return new TopicFragment();
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    public void initView() {

    }

    @Override
    public ITopic.Presenter createPersenter() {
        return new TopicPresenter();
    }

    @Override
    public void initData() {

    }

}
