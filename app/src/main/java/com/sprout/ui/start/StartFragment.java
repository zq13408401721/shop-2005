package com.sprout.ui.start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sprout.R;

public class StartFragment extends Fragment {

    ImageView imgBg;
    TextView txtComein;

    int current; //当前的编号
    int total; //总的引导页面的数量

    View.OnClickListener listener;

    public void addOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }


    public static StartFragment getInstance(int index,int total){
        StartFragment fragment = new StartFragment();
        fragment.current = index;
        fragment.total = total;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_start,null,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgBg = view.findViewById(R.id.img_bg);
        int rid = 0;
        if(current == 1){
            rid = R.mipmap.ic_1;
        }else if(current == 2){
            rid = R.mipmap.ic_2;
        }else if(current == 3){
            rid = R.mipmap.ic_3;
        }
        imgBg.setImageResource(rid);
        txtComein = view.findViewById(R.id.txt_comein);
        txtComein.setOnClickListener(this.listener);
        if(current == total){
            txtComein.setVisibility(View.VISIBLE);
        }else{
            txtComein.setVisibility(View.GONE);
        }
    }
}
