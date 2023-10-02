package com.example.android_mymedia.search.searchrepository

import com.example.android_mymedia.search.searchdata.SearchListModel

interface SearchRepository {
    suspend fun getPopularVideo(token: String?,category : String): Pair<List<SearchListModel>, String>
    suspend fun getSearch(query: String): List<SearchListModel>?
}