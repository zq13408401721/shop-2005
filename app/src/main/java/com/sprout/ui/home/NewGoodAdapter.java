package com.sprout.ui.home;

import android.content.Context;
import android.content.pm.PackageInfo;
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
import com.sprout.app.Constants;
import com.sprout.mode.data.HomeBean;
import com.sprout.utils.ImageLoader;
import com.sprout.utils.TextViewUtils;

import java.util.List;

public class NewGoodAdapter extends DelegateAdapter.Adapter<NewGoodAdapter.ViewHolder> {

    Context context;
    List<HomeBean.DataBean.NewGoodsListBean> list;

    public NewGoodAdapter(Context context, List<HomeBean.DataBean.NewGoodsListBean> list){
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
        View view = LayoutInflater.from(context).inflate(R.layout.layout_newgood_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageLoader.imageLoad(list.get(position).getList_pic_url(),holder.imgNewGood);
        TextViewUtils.setTextView(list.get(position).getName(),holder.txtNewGoodName);
        String price = Constants.price_word.replace("$",list.get(position).getRetail_price());
        TextViewUtils.setTextView(price,holder.txtNewGoodPrice);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgNewGood;
        TextView txtNewGoodName;
        TextView txtNewGoodPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNewGood = itemView.findViewById(R.id.img_newgood);
            txtNewGoodName = itemView.findViewById(R.id.txt_newgoodname);
            txtNewGoodPrice = itemView.findViewById(R.id.txt_newgoodprice);

        }
    }
}
