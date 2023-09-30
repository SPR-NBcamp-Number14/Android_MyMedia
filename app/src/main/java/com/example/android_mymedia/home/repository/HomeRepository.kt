package com.example.android_mymedia.home.repository

import com.example.android_mymedia.home.data.ButtonModel
import com.example.android_mymedia.home.data.PlayListModel

interface HomeRepository {
    suspend fun getPopularVideo(token: String?,category : String): Pair<List<PlayListModel>, String>
    suspend fun getCategory(): List<ButtonModel>?
}