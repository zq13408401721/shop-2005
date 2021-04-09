package com.sprout.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sprout.R;
import com.sprout.mode.data.NewGoodsBean;
import com.sprout.utils.TextViewUtils;

import java.util.List;

public class FilterWindow extends PopupWindow {

    Context context;
    View view;
    RecyclerView recyclerView;
    List<NewGoodsBean.DataBeanX.FilterCategoryBean> filters;

    FilterItemAdapter filterItemAdapter;

    //由创建Popupwindow的类来实现点击事件
    View.OnClickListener click;


    public FilterWindow(Context context, List<NewGoodsBean.DataBeanX.FilterCategoryBean> filters){
        this.context = context;
        this.filters = filters;
        initView();
    }

    /**
     * 类似接口回调
     * @param onClickListener
     */
    public void addOnClickListener(View.OnClickListener onClickListener){
        click = onClickListener;
    }


    private void initView(){
        view = LayoutInflater.from(context).inflate(R.layout.layout_filter_window,null,false);
        setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击()
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 实例化一个ColorDrawable颜色为半透明
        /*ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);*/
        //this.backgroundAlpha(context, 0.5f);//0.0-1.0

        recyclerView = view.findViewById(R.id.recyclerView);
        filterItemAdapter = new FilterItemAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(filterItemAdapter);
    }

    class FilterItemAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_filter_item,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TextView txt = (TextView) holder.itemView;
            TextViewUtils.setTextView(filters.get(position).getName(),txt);
            //筛选条件的id
            txt.setTag(filters.get(position).getId());
            if(click != null){
                txt.setOnClickListener(click);
            }
        }

        @Override
        public int getItemCount() {
            return filters.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
