package com.sprout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sprout.app.Constants;
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
        Intent intent = getIntent();
        intent.putExtra("test",100);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("type")){
                int type = intent.getIntExtra("type",0);
                intent.removeExtra("type");
                if(type == Constants.PAGE_REQEST_CODE_GOODDETAIL){
                    gotoCar();
                }else if(type == Constants.PAGE_WXLOGIN_CODE){
                    //个人信息界面
                    gotoMine();
                }
            }
        }
        Log.i("TAG","onResume");
    }

    /**
     * 接收界面回传的方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG",String.valueOf(requestCode));
        if(requestCode == Constants.PAGE_REQEST_CODE_GOODDETAIL){
            gotoCar();
        }
    }

    private void gotoCar(){
        bottomNavigation.setSelectedItemId(R.id.menu_car);
        /*currentItemId = R.id.menu_car;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentbox,carFragment).commit();*/
    }

    private void gotoMine(){
        if(currentItemId == R.id.menu_mine){
            if(mineFragment != null){
                ((MineFragment)mineFragment).wxUpdateInfo();
            }
        }else{
            bottomNavigation.setSelectedItemId(R.id.menu_mine);
        }
    }
}