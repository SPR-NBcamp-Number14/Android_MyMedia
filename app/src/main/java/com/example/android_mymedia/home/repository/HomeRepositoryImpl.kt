package com.example.android_mymedia.home.repository

import com.example.android_mymedia.retrofit.RetrofitClient

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getShortsList() {
        val responseShort = RetrofitClient.api.getPlayListItems(playlistID = "")


    }

}