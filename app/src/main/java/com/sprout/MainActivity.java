package com.sprout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sprout.base.BaseActivity;
import com.sprout.interfaces.home.IMain;
import com.sprout.ui.car.CarFragment;
import com.sprout.ui.home.HomeFragment;
import com.sprout.ui.mine.MineFragment;
import com.sprout.ui.sort.SortFragment;
import com.sprout.ui.topic.TopicFragment;

import butterknife.BindView;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity<IMain.Presenter> implements IMain.View {

    @BindView(R.id.fragmentbox)
    FrameLayout fragmentbox;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    Fragment homeFragment;
    Fragment topicFragment;
    Fragment sortFragment;
    Fragment carFragment;
    Fragment mineFragment;

    int currentItemId; //当前的页面所对应的ItemID


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        initFragments();
        //bottomnavigation 选中颜色的设置
        ColorStateList colorStateList = getResources().getColorStateList(R.color.bottomnavigation_color);
        bottomNavigation.setItemTextColor(colorStateList);
        bottomNavigation.setItemIconTintList(colorStateList);
        FragmentManager fragmentManager = getSupportFragmentManager();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(currentItemId == item.getItemId()){
                    return false;
                }
                currentItemId = item.getItemId();
                FragmentTransaction transaction;
                switch (item.getItemId()){
                    case R.id.menu_home:
                        item.setChecked(true);
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragmentbox,homeFragment).commit();
                        break;
                    case R.id.menu_topic:
                        item.setChecked(true);
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragmentbox,topicFragment).commit();
                        break;
                    case R.id.menu_sort:
                        item.setChecked(true);
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragmentbox,sortFragment).commit();
                        break;
                    case R.id.menu_car:
                        item.setChecked(true);
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragmentbox,carFragment).commit();
                        break;
                    case R.id.menu_mine:
                        item.setChecked(true);
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragmentbox,mineFragment).commit();
                        break;
                }
                return false;
            }
        });

        initHome();
    }

    private void initFragments(){
        homeFragment = HomeFragment.getInstance();
        topicFragment = TopicFragment.getInstance();
        sortFragment = SortFragment.getInstance();
        carFragment = CarFragment.getInstance();
        mineFragment = MineFragment.getInstance();
    }

    /**
     * 初始化显示第一个页面
     */
    private void initHome(){
        currentItemId = R.id.menu_home;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentbox,homeFragment).commit();
    }

    @Override
    protected IMain.Presenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}