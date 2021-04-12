package com.sprout.ui.sort;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sprout.R;
import com.sprout.mode.data.CatalogBean;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;

import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> {


    List<CatalogBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list;
    Context context;

    ItemClick itemClick;

    public void addItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    public SortAdapter(Context context, List<CatalogBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sort_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CatalogBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean = list.get(position);
        ImageLoader.imageLoad(bean.getWap_banner_url(),holder.imgSort);
        TextViewUtils.setTextView(bean.getName(),holder.txtName);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClick != null){
                    int pos = (int) v.getTag();
                    itemClick.onItemClick(pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgSort;
        TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSort = itemView.findViewById(R.id.img_sort);
            txtName = itemView.findViewById(R.id.txt_name);
        }
    }

    public interface ItemClick{
        void onItemClick(int pos);
    }
}
