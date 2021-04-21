package com.sprout.mode.data;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

    List<MemberBean> members = new ArrayList<>();
    List<FirendBean> firends = new ArrayList<>();

    private void getDataReturn(){
        for(MemberBean item : members){
            FirendBean bean = new FirendBean();
            bean.setStudent(item.getStudent());
            bean.setKey(item.getKey());
            firends.add(bean);
        }
    }

}
