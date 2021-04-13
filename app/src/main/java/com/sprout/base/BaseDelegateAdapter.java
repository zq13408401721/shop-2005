package com.sprout.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.sprout.app.Delegate;

import java.util.List;

/**
 * delegate基类
 */
public abstract class BaseDelegateAdapter<D> extends DelegateAdapter.Adapter<BaseDelegateAdapter.BaseViewHolder> {



    protected Context context;
    protected List<D> list;
    protected String title;
    Delegate delegate;
    protected D data;
    protected View.OnClickListener itemListener;//点击事件的接口回调

    public BaseDelegateAdapter(Context context, List<D> list, Delegate delegate){
        this.context = context;
        this.list = list;
        this.delegate = delegate;
    }

    public BaseDelegateAdapter(Context context,String title,Delegate delegate){
        this.context = context;
        this.title = title;
        this.delegate = delegate;
    }

    public BaseDelegateAdapter(Context context,D data,Delegate delegate){
        this.context = context;
        this.data = data;
        this.delegate = delegate;
    }

    /**
     * 刷新值
     * @param data
     */
    public void refreshData(D data){
        if(delegate == Delegate.OBJECT){
            this.data = data;
            notifyDataSetChanged();
        }
    }

    /**
     * 设置点击事件的接口回调
     * @param listener
     */
    public void addEventListener(View.OnClickListener listener){
        itemListener = listener;
    }


    //页面的布局类型
    protected abstract LayoutHelper getLayoutHelper();

    protected abstract int getLayoutId();

    protected abstract void bindData(BaseDelegateAdapter.BaseViewHolder holder,D data);

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return getLayoutHelper();
    }

    @Override
    public int getItemCount() {
        if(delegate == Delegate.TITLE){
            return title == null || title.length() == 0 ? 0 : 1;
        }else if(delegate == Delegate.LIST){
            return list == null ? 0 : list.size();
        }else if(delegate == Delegate.OBJECT){
            return data == null ? 0 : 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayoutId(),parent,false);
        BaseViewHolder vh = new BaseViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDelegateAdapter.BaseViewHolder holder, int position) {

        if(delegate == Delegate.TITLE){
            bindData(holder, (D) title);
        }else if(delegate == Delegate.LIST){
            bindData(holder,list.get(position));
        }else if(delegate == Delegate.OBJECT){
            bindData(holder,data);
        }

    }

    public class BaseViewHolder extends RecyclerView.ViewHolder{

        SparseArray<View> views = new SparseArray<>();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * <V extends View> 声明一个类型 V
         * @param id
         * @return V
         */
        public View getViewById(int id){
            View view = views.get(id);
            if(view == null){
                view = itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
}
