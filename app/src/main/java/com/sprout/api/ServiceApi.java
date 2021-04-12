package com.sprout.api;


import com.sprout.mode.data.CatalogBean;
import com.sprout.mode.data.CatalogTabBean;
import com.sprout.mode.data.CategoryListBean;
import com.sprout.mode.data.CategoryTopBean;
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

    /**
     * 分类商品详情也的顶部数据接口  主页频道入口的商品数据
     * @param id
     * @return
     */
    @GET("api/goods/category")
    Flowable<CategoryTopBean> getCategoryTop(@Query("id") int id);

    /**
     * 分类详情页列表数据
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GET("api/goods/list")
    Flowable<CategoryListBean> getCategoryList(@Query("id") int id,@Query("page") int page,@Query("size") int size);




}
