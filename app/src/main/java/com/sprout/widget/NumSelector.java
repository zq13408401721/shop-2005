package com.sprout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sprout.R;

public class NumSelector extends LinearLayout implements View.OnClickListener {

    private Context context;

    TextView txtReduce;
    TextView txtNum;
    TextView txtAdd;

    private int number;

    private INumberUpdate numberUpdate;
    public void addNumberUpdate(INumberUpdate numberUpdate){
        this.numberUpdate = numberUpdate;
    }

    public NumSelector(Context context) {
        super(context);
    }

    public NumSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NumSelector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化界面
     */
    public void initView(){

        txtReduce = findViewById(R.id.txt_reduce);
        txtNum = findViewById(R.id.txt_number);
        txtAdd = findViewById(R.id.txt_add);

        txtReduce.setOnClickListener(this);
        txtAdd.setOnClickListener(this);

    }

    public void updateNumber(int num){
        number = num;
        txtNum.setText(String.valueOf(number));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_reduce:
                reduce();
                break;
            case R.id.txt_add:
                add();
                break;
        }
    }

    private void reduce(){
        number--;
        number = number <= 0 ? 1 : number;
        txtNum.setText(String.valueOf(number));
        if(numberUpdate != null){
            numberUpdate.update(number);
        }
    }

    private void add(){
        number ++;
        txtNum.setText(String.valueOf(number));
        if(numberUpdate != null){
            numberUpdate.update(number);
        }
    }

    /**
     * 接口定义
     */
    public interface INumberUpdate{
        void update(int num);
    }
}
