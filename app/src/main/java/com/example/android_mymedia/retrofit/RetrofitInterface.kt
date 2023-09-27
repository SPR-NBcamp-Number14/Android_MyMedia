package com.example.android_mymedia.retrofit

import com.example.android_mymedia.home.data.ResponseVideo
import com.example.android_mymedia.searchdata.ResponseSearch
import com.example.android_mymedia.unit.Unit.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("videos")
    suspend fun getVideo(
        @Query("part") part: String = "id,snippet,statistics",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") region: String = "KR",
        @Query("maxResults") maxResults: Int = 20, // 20정도가 적당
        @Query("key") apiKey:String = API
    ): ResponseVideo

    @GET("search")
    suspend fun getSearch(
        @Query("part") part : String ="snippet",
        @Query("regionCode") region: String = "KR",
        @Query("maxResults") maxResults: Int = 20,
        @Query("order") order:String = "date",
        @Query("q") q:String,
        @Query("videoType") videoType: String ="any",
        @Query("type") type : String ="channel,playlist,video",
        @Query("key") apiKey:String = API
    ):ResponseSearch

}