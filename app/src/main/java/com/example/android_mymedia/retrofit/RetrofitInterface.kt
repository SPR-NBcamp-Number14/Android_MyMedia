package com.example.android_mymedia.retrofit


import com.example.android_mymedia.home.data.api.ResponseCategory
import com.example.android_mymedia.home.data.api.ResponseVideo
import com.example.android_mymedia.search.searchdata.ResponseSearch
import com.example.android_mymedia.unit.Unit.API
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("videos")
    suspend fun getVideo(
        @Query("part") part: String = "id,snippet,statistics",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") region: String = "KR",
        @Query("maxResults") maxResults: Int = 10, // 20정도가 적당
        @Query("key") apiKey: String = API,
        @Query("pageToken") pageToken: String? = null,
        @Query("videoCategoryId") videoCategoryId: String = "0"
    ): ResponseVideo

    @GET("videoCategories")
    suspend fun getCategory(
        @Query("part") part: String = "snippet",
        @Query("hl") hl: String = "ko_KR",
        @Query("regionCode") region: String = "KR",
        @Query("key") apiKey: String = API,
    ): ResponseCategory

    @GET("search")
    suspend fun getSearch(
        @Query("part") part: String = "snippet",
        @Query("regionCode") region: String = "KR",
        @Query("maxResults") maxResults: Int = 20,
        @Query("order") order: String = "date",
        @Query("q") q: String, //검색어는 유지 카테고리 값으로 재검색
        @Query("videoType") videoType: String = "any",
        @Query("type") type: String = "channel,playlist,video",
        @Query("key") apiKey: String = API
    ): ResponseSearch

    @GET("search")
    suspend fun getSearchCategory(
        @Query("part") part: String = "snippet",
        @Query("regionCode") region: String = "KR",
        @Query("maxResults") maxResults: Int = 20,
        @Query("order") order: String = "date",
        @Query("q") q: String,
        @Query("videoType") videoType: String = "any",
        @Query("type") type: String = "video",
        @Query("key") apiKey: String = API,
        @Query("videoCategoryId") videoCategoryId: String,
    ): ResponseSearch

}