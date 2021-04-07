package com.sprout.api;


import com.sprout.mode.data.ChannelBean;
import com.sprout.mode.data.HomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ServiceApi {
    String BASE_URL = "https://cdplay.cn/";


    @GET("api/index")
    Flowable<HomeBean> getHome();



}
