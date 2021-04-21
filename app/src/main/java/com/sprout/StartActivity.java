package com.sprout;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.sprout.base.BaseActivity;
import com.sprout.interfaces.start.IStart;
import com.sprout.test.EditTextActivity;
import com.sprout.test.TouchEventActivity;
import com.sprout.ui.start.StartFragment;
import com.sprout.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *启动页
 */
public class StartActivity extends BaseActivity<IStart.Presenter> implements IStart.View,View.OnClickListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.img_one)
    ImageView imgOne;
    @BindView(R.id.img_two)
    ImageView imgTwo;
    @BindView(R.id.img_three)
    ImageView imgThree;
    @BindView(R.id.layout_dot)
    LinearLayout layoutDot;

    List<StartFragment> fragments;
    MyPagerAdapter myPagerAdapter;



    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {

        boolean bool = SpUtils.getInstance().getBoolean("iscomein"); //是否进入过app
        if(!bool){
            layoutDot.setVisibility(View.GONE);
            initFragments();
            myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(myPagerAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    updateDot(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }else{

        }

    }

    private void initFragments(){
        fragments = new ArrayList<>();
        for(int i=0; i<3; i++){
            StartFragment startFragment = StartFragment.getInstance(i+1,3);
            startFragment.addOnClickListener(this);
            fragments.add(startFragment);
        }
    }


    @Override
    public void onClick(View v) {

        SpUtils.getInstance().setValue("iscomein",true);
        //进入app
        comeInApp();
    }

    private void comeInApp(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 更新底部的原点显示效果
     * @param pos
     */
    private void updateDot(int pos){
        imgOne.setImageResource(R.drawable.img_dot_normal);
        imgTwo.setImageResource(R.drawable.img_dot_normal);
        imgThree.setImageResource(R.drawable.img_dot_normal);
        if(pos == 0){
            imgOne.setImageResource(R.drawable.img_dot_select);
        }else if(pos == 1){
            imgTwo.setImageResource(R.drawable.img_dot_select);
        }else if(pos == 2){
            imgThree.setImageResource(R.drawable.img_dot_select);
        }
    }

    @Override
    protected IStart.Presenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
