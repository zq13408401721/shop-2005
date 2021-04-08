package com.sprout.ui.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.sprout.R;
import com.sprout.utils.SpUtils;

public class SettingActivity extends AppCompatActivity {


    CheckBox checkBox_img;
    CheckBox checkBox_noimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        checkBox_img = findViewById(R.id.checkbox_img);
        checkBox_noimg = findViewById(R.id.checkbox_noimg);

        initView();
    }

    private void initView() {
        boolean bool = SpUtils.getInstance().getBoolean("imageloader");
        if(bool){
            checkBox_img.setChecked(true);
            checkBox_noimg.setChecked(false);
        }else{
            checkBox_noimg.setChecked(true);
            checkBox_img.setChecked(false);
        }

        checkBox_img.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBox_noimg.setChecked(false);
                    SpUtils.getInstance().setValue("imageloader",true);
                }else{
                    checkBox_noimg.setChecked(true);
                }
            }
        });

        checkBox_noimg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBox_img.setChecked(false);
                    SpUtils.getInstance().setValue("imageloader",false);
                }else{
                    checkBox_img.setChecked(true);
                }
            }
        });
    }
}