package com.benone.mvvm3.network

import com.benone.mvvm3.bean.CommentBean
import com.benone.mvvm3.bean.FindBean
import com.benone.mvvm3.bean.HomeBean
import com.benone.mvvm3.bean.HotBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {

    companion object {
        val BASE_URL: String
            get() {
                return "http://baobab.kaiyanapp.com/api/"
            }
    }

    //获取第一页的数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<HomeBean>

    //获取第一页之后的数据
    @GET("v2/feed")
    fun getHomeMoreData(
        @Query("date") date: String,
        @Query("num") num: String
    ): Observable<HomeBean>

    //获取发现频道数据
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getFindData(): Observable<MutableList<FindBean>>

    //获取热门排行信息
    @GET("v3/ranklist")
    fun getHotData(
        @Query("num") num: Int,
        @Query("strategy") strategy: String,
        @Query("udid") udid: String,
        @Query("vc") vc: Int
    ): Observable<HotBean>

    //获取发现频道详细信息
    @GET("v3/videos")
    fun getFindDetailData(
        @Query("categoryName") categoryName: String,
        @Query("strategy") strategy: String,
        @Query("udid") udid: String,
        @Query("vc") vc: Int
    ): Observable<HotBean>

    //获取发现详情加载更多信息
    @GET("v3/videos")
    fun geyFindDetailMoreData(
        @Query("start") start: Int,
        @Query("num") num: Int,
        @Query("categoryName") categoryName: String,
        @Query("strategy") strategy: String
    ): Observable<HotBean>

    //获取关键词搜索相关信息
    @GET("v1/search")
    fun getSearchData(
        @Query("num") num: Int,
        @Query("query") query: String,
        @Query("start") start: Int
    ): Observable<HotBean>

    //获取评论列表
    @GET("v1/replies/video")
    fun getCommentListData(
        @Query("num") num: Int,
        @Query("id") id: Int,
        @Query("lastId") lastId: String
    ): Observable<CommentBean>
}