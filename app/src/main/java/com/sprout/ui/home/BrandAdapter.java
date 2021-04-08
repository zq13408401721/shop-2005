package com.sprout.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.sprout.R;
import com.sprout.mode.data.HomeBean;
import com.youth.banner.Banner;

import java.util.List;

public class BrandAdapter extends DelegateAdapter.Adapter<BrandAdapter.ViewHolder> {

    Context context;
    List<HomeBean.DataBean.BrandListBean> list;

    public BrandAdapter(Context context, List<HomeBean.DataBean.BrandListBean> list){
        this.context = context;
        this.list = list;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new GridLayoutHelper(2);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_brand_item,parent,false);
        BrandAdapter.ViewHolder viewHolder  = new BrandAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBean.DataBean.BrandListBean bean = list.get(position);
        Glide.with(context).load(bean.getNew_pic_url()).into(holder.imgBrand);
        if(!bean.getName().isEmpty()){
            holder.txtName.setText(bean.getName());
        }
        if(!bean.getFloor_price().isEmpty()){
            holder.txtPrice.setText(bean.getFloor_price());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgBrand; //imageview -> T extends View
        TextView txtName;   //textview -> T extends View
        TextView txtPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBrand = itemView.findViewById(R.id.img_brand);
            txtName = itemView.findViewById(R.id.txt_name);
            txtPrice = itemView.findViewById(R.id.txt_price);
        }
    }
}
