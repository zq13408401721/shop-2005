package com.sprout.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;

public class TitleAdapter extends DelegateAdapter.Adapter<TitleAdapter.ViewHolder> {

    Context context;
    String title;

    public TitleAdapter(Context context,String title){
        this.context = context;
        this.title = title;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_title,parent,false);
        TitleAdapter.ViewHolder viewHolder  = new TitleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(!title.isEmpty()){
            holder.txtTitle.setText(title);
        }
    }

    @Override
    public int getItemCount() {
        //return title.length() == 0 ? 0 : 1;
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
        }
    }


}
