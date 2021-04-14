package com.sprout.utils;

import android.app.Activity;

import com.sprout.MainActivity;

import java.util.Stack;

public class ActivityTask {
    /**
     * 栈对象,用于存放Activity对象，以便于管理
     */
    private static Stack<Activity> mStack = new Stack<>();


    /**
     * 获取管理栈集合
     */
    public static Stack<Activity> getActivitiesList() {
        return mStack;
    }

    /**
     * 弹出Activity
     *
     * @param activity
     * @param isFinish 是否手动finish掉activity
     * @see [类、类#方法、类#成员]
     */
    public static void popActivity(Activity activity, boolean isFinish) {
        if (activity != null) {
            if (isFinish) {
                activity.finish();
            }
            mStack.remove(activity);
        }
    }
    /**
     * 获取栈顶的Activity
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Activity currentActivity() {
        if (!mStack.isEmpty()) {
            // 该方法Returns the last element in this vector.也就是说栈顶的
            return (Activity) mStack.lastElement();
        } else {
            return null;
        }
    }

    /**
     * 获取栈的Acitivity
     *
     * @param dept 索引（倒叙形式）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Activity getNextActivity(int dept) {
        // 反序返回指定索引号的Activity实例，但不做弹出栈操作
        if (dept <= mStack.size()) {
            return mStack.get(dept);
        } else {
            return null;
        }
    }

    /**
     * Activity入栈
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public static void addActivity(Activity activity) {
        if (mStack == null) {
            mStack = new Stack<Activity>();
        }
        mStack.add(activity);
    }

    /**
     * 清空Activity栈
     *
     * @see [类、类#方法、类#成员]
     */
    public static void removeAllActivity() {
        while (mStack != null && mStack.size() > 0) {
            Activity activity = currentActivity();
            if (activity != null) {
                popActivity(activity, true);
            }
        }
    }

    /**
     * 回退到mainactivity
     */
    public static void gotoMainActivity(int type){
        while (mStack != null && mStack.size() > 0) {
            Activity activity = currentActivity();
            if (activity != null) {
                if(activity.getLocalClassName().equals(MainActivity.class.getSimpleName())){
                    activity.getIntent().putExtra("type",type);
                    break;
                }
                popActivity(activity, true);
            }
        }
    }


    /**
     * 获取指定的Acitivity
     *
     * @param （倒叙形式）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Activity getFoundCampaignDetailActivity() {
        Activity activity = null;
        if (!mStack.isEmpty() && mStack.size() > 0) {
            for (int i = 0; i < mStack.size(); i++) {
//                if (getNextActivity(i) instanceof FoundCampaignDetailActivity)
//                {
//                    activity = getNextActivity(i);
//                    break;
//                }

            }
        }
        return activity;
    }
}
