package com.sprout.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.sprout.R;
import com.sprout.mode.data.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.BannerViewHolder> {

    List<HomeBean.DataBean.BannerBean> list;
    public BannerAdapter(List<HomeBean.DataBean.BannerBean> list){
        this.list = list;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner,parent,false);
        BannerViewHolder bannerViewHolder = new BannerViewHolder(view);
        return bannerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        holder.banner.setImageLoader(new MyImageLoader());
        holder.banner.setImages(list);
        holder.banner.setDelayTime(1500);
        holder.banner.start();
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * banner所在列表条目的ViewHolder
     */
    class BannerViewHolder extends RecyclerView.ViewHolder{

        Banner banner;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    /**
     * banner数据加载器
     */
    class MyImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object item, ImageView imageView) {
            HomeBean.DataBean.BannerBean data = (HomeBean.DataBean.BannerBean) item;
            Glide.with(context).load(data.getImage_url()).into(imageView);
        }
    }
}
