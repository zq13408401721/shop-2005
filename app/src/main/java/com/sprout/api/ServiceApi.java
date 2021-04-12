package com.sprout.api;


import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.mode.data.HomeBean;
import com.sprout.mode.data.NewGoodTopBean;
import com.sprout.mode.data.NewGoodsBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ServiceApi {
    String BASE_URL = "https://cdplay.cn/";


    @GET("api/index")
    Flowable<HomeBean> getHome();

    @GET("api/goods/list")
    Flowable<NewGoodsBean> getNewGoods(@QueryMap Map<String,String> map);

    //热门商品顶部的数据
    @GET("api/goods/hot")
    Flowable<NewGoodTopBean> getNewGoodsTop();

    /**
     * 分类竖导航的数据接口
     * @return
     */
    @GET("api/catalog/index")
    Flowable<CatalogTabBean> getCatalogTab(@Query("id") int id);

    /**
     * 竖导航所对应的分类数据
     * @param id
     * @return
     */
    @GET("api/catalog/current")
    Flowable<CatalogBean> getCatalog(@Query("id") int id);



}
