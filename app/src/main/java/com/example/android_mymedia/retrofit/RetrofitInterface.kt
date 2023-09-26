package com.example.android_mymedia.retrofit

import com.example.android_mymedia.home.data.ResponseVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("videos")
    suspend fun getVideo(
        @Query("part") part: String = "id,snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") region: String = "KR",
        @Query("maxResults") maxResults: Int = 20 // 20정도가 적당
    ): ResponseVideo

}