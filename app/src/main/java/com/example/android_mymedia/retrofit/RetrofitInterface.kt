package com.example.android_mymedia.retrofit

import com.example.android_mymedia.home.data.ResponsePlayList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("playlistItems")
    suspend fun getPlayListItems(
        @Query("part") part: String = "snippet",
        @Query("id") playlistID: String,
        @Query("maxResults") maxResults: Int = 20 // 20정도가 적당
    ): ResponsePlayList
}