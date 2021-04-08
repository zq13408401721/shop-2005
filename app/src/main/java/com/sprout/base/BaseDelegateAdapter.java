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

import java.util.List;

/**
 * delegate基类
 */
public abstract class BaseDelegateAdapter<D> extends DelegateAdapter.Adapter<BaseDelegateAdapter.BaseViewHolder> {

    Context context;
    List<D> list;
    String title;

    public BaseDelegateAdapter(Context context,List<D> list){
        this.context = context;
        this.list = list;
    }

    public BaseDelegateAdapter(Context context,String title){
        this.context = context;
        this.title = title;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return getLayoutHelper();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayoutId(),parent,false);
        BaseViewHolder vh = new BaseViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder,list.get(position));
    }

    @Override
    public int getItemCount() {
        if(title != null){
            return title.length() == 0 ? 0 : 1;
        }
        return list.size();
    }

    //页面的布局类型
    protected abstract LayoutHelper getLayoutHelper();

    protected abstract int getLayoutId();

    protected abstract void bindData(BaseViewHolder holder,D data);

    public class BaseViewHolder extends RecyclerView.ViewHolder{

        SparseArray<View> views = new SparseArray<>();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * <V extends View> 声明一个类型 V
         * @param id
         * @param <V>
         * @return V
         */
        public <V extends View> V getViewById(int id){
            V view = (V)views.get(id);
            if(view == null){
                view = (V) itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }
}
