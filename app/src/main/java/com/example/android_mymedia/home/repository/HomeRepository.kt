package com.example.android_mymedia.home.repository

import com.example.android_mymedia.home.data.PlayListModel

interface HomeRepository {
    suspend fun getPopularVideo(): Pair<List<PlayListModel>,String>
}