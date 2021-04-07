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
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.bumptech.glide.Glide;
import com.sprout.R;
import com.sprout.mode.data.HomeBean;

import java.util.List;

public class ChannelAdapter extends DelegateAdapter.Adapter<ChannelAdapter.ViewHolder> {

    Context context;
    List<HomeBean.DataBean.ChannelBean> list;

    public ChannelAdapter(Context context, List<HomeBean.DataBean.ChannelBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new ColumnLayoutHelper();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_channel_item,parent,false);
        ViewHolder viewHolder  = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBean.DataBean.ChannelBean channelBean = list.get(position);
        if(!channelBean.getName().isEmpty()){
            holder.txtChannel.setText(channelBean.getName());
            Glide.with(context).load(channelBean.getIcon_url()).into(holder.imgChannel);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * channel的viewholder
     */
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgChannel;
        TextView txtChannel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChannel = itemView.findViewById(R.id.img_channel);
            txtChannel = itemView.findViewById(R.id.txt_channel);
        }
    }
}
